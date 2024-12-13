package com.qtech.im.aa.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.service.IAaListParamsHistoryCheckResultService;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import com.qtech.im.common.util.QtechImVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsCheckResult aaListParamsCheckResult) {
        startPage();
        QtechImVoUtil.QtechImVos<AaListParamsCheckResultVo> list = aaListParamsCheckResultService.selectAaListParamsCheckResultList(aaListParamsCheckResult);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @Log(title = "AA List历史点检记录导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsCheckResult aaListParamsCheckResult) {
        QtechImVoUtil.QtechImVos<AaListParamsCheckResultVo> list = aaListParamsCheckResultService.selectAaListParamsCheckResultList(aaListParamsCheckResult);
        ExcelUtil<AaListParamsCheckResultVo> util = new ExcelUtil<>(AaListParamsCheckResultVo.class);
        util.exportExcel(response, list.getData(), "AA List历史点检记录导出");
    }
}
