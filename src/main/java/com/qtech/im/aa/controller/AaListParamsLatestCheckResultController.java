package com.qtech.im.aa.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsCheckResultVo;
import com.qtech.im.aa.service.IAaListParamsLatestCheckResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 09:31:24
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/latest/status")
public class AaListParamsLatestCheckResultController extends BaseController {

    @Autowired
    private IAaListParamsLatestCheckResultService aaListParamsLatestCheckResultService;

    @GetMapping("/list")
    public TableDataInfo list(AaListParamsCheckResultVo aaListParamsCheckResultVo) {
        startPage();
        return getDataTable(aaListParamsLatestCheckResultService.selectAaListParamsLatestCheckResultList(aaListParamsCheckResultVo));
    }

    @GetMapping("/factoryNames")
    public R<List<AaListParamsCheckResultVo>> getFactoryNames() {
        List<AaListParamsCheckResultVo> list = aaListParamsLatestCheckResultService.selectFactoryNameList();
        return R.ok(list);
    }

    @GetMapping("/groupNames")
    public R<List<AaListParamsCheckResultVo>> getGroupNames(AaListParamsCheckResultVo aaListParamsCheckResultVo) {
        List<AaListParamsCheckResultVo> list = aaListParamsLatestCheckResultService.selectGroupNameList(aaListParamsCheckResultVo);
        return R.ok(list);
    }
}
