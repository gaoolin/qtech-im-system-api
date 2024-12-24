package com.qtech.im.aa.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsEqCtrl;
import com.qtech.im.aa.service.IAaListParamsEqCtrlService;
import com.qtech.im.aa.vo.AaListParamsEqCtrlVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:05:16
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/eq")
public class AaListParamsEqCtrlController extends BaseController {

    private final IAaListParamsEqCtrlService aaListParamsEqCtrlService;

    @Autowired
    public AaListParamsEqCtrlController(IAaListParamsEqCtrlService aaListParamsEqCtrlService) {
        this.aaListParamsEqCtrlService = aaListParamsEqCtrlService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsEqCtrlVo aaListParamsEqCtrlVo) {
        startPage();
        List<AaListParamsEqCtrlVo> list = aaListParamsEqCtrlService.list(aaListParamsEqCtrlVo);
        return getDataTable(list);
    }

    @Log(title = "AA机台受控状态修改", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/ignore", method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult changeSwitch(@RequestBody AaListParamsEqCtrl aaListParamsEqCtrl) {
        Boolean changed = false;
        Boolean exist = aaListParamsEqCtrlService.isExist(aaListParamsEqCtrl);
        if (exist) {
            changed = aaListParamsEqCtrlService.edit(aaListParamsEqCtrl);
        } else {
            changed = aaListParamsEqCtrlService.addOne(aaListParamsEqCtrl);
        }
        return toAjax(changed);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult edit(@RequestBody AaListParamsEqCtrl aaListParamsEqCtrl) {
        boolean updated = aaListParamsEqCtrlService.edit(aaListParamsEqCtrl);
        return toAjax(updated);
    }

    @Log(title = "AA List管控设备状态导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsEqCtrlVo aaListParamsEqCtrlVo) {
        List<AaListParamsEqCtrlVo> list = aaListParamsEqCtrlService.list(aaListParamsEqCtrlVo);
        ExcelUtil<AaListParamsEqCtrlVo> util = new ExcelUtil<>(AaListParamsEqCtrlVo.class);
        util.exportExcel(response, list, "AA List管控设备状态");
    }
}