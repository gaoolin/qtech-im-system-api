package com.qtech.im.aa.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsEq;
import com.qtech.im.aa.service.IAaListParamsEqService;
import com.qtech.im.aa.vo.AaListParamsEqVo;
import com.qtech.im.common.util.QtechImVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:05:16
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/eq")
public class AaListParamsEqController extends BaseController {

    private final IAaListParamsEqService aaListParamsEqService;

    @Autowired
    public AaListParamsEqController(IAaListParamsEqService aaListParamsEqService) {
        this.aaListParamsEqService = aaListParamsEqService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsEq aaListParamsEq) {
        startPage();
        QtechImVoUtil.QtechImVos<AaListParamsEqVo> list = aaListParamsEqService.selectAaListParamsEqList(aaListParamsEq);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @Log(title = "AA机台受控状态修改", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/ignore", method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult changeSwitch(@RequestBody AaListParamsEq aaListParamsEq) {
        int i = 0;
        Boolean b = aaListParamsEqService.isExist(aaListParamsEq);
        if (b) {
            i = aaListParamsEqService.editAaListParamsIgnoreEq(aaListParamsEq);
        } else {
            i = aaListParamsEqService.insertAaListParamsIgnoreEq(aaListParamsEq);
        }
        return toAjax(i);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult edit(@RequestBody AaListParamsEq aaListParamsEq) {
        int i = aaListParamsEqService.editAaListParamsEq(aaListParamsEq);
        return toAjax(i);
    }

    @Log(title = "AA List管控设备状态导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsEq aaListParamsEq) {
        QtechImVoUtil.QtechImVos<AaListParamsEqVo> list = aaListParamsEqService.selectAaListParamsEqList(aaListParamsEq);
        ExcelUtil<AaListParamsEqVo> util = new ExcelUtil<>(AaListParamsEqVo.class);
        util.exportExcel(response, list.getData(), "AA List管控设备状态");
    }
}
