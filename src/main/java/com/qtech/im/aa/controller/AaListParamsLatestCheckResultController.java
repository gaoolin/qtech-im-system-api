package com.qtech.im.aa.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.service.IAaListParamsLatestCheckResultService;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import com.qtech.im.common.util.QtechImVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public TableDataInfo list(AaListParamsCheckResult aaListParamsCheckResult) {
        startPage();
        QtechImVoUtil.QtechImVos<AaListParamsCheckResultVo> list = aaListParamsLatestCheckResultService.selectAaListParamsLatestCheckResultList(aaListParamsCheckResult);
        return QtechImVoUtil.getVoDataTable(list);
    }
}
