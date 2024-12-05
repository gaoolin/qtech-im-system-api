package com.qtech.im.aa.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.service.IAaListParamsHistoryCheckResultService;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:42:19
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/history/status")
public class AaListParamsHistoryCheckResultController extends BaseController {

    @Autowired
    private IAaListParamsHistoryCheckResultService aaListParamsCheckResultService;

    @RequestMapping("/list")
    public TableDataInfo list(AaListParamsCheckResult aaListParamsCheckResult) {
        startPage();
        List<AaListParamsCheckResultVo> list = aaListParamsCheckResultService.selectAaListParamsCheckResultList(aaListParamsCheckResult);
        return getDataTable(list);
    }
}
