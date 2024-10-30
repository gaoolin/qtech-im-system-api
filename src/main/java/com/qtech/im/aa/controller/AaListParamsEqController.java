package com.qtech.im.aa.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsEqVo;
import com.qtech.im.aa.service.IAaListParamsEqService;
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
public class AaListParamsEqController extends BaseController {

    private final IAaListParamsEqService aaListParamsEqService;

    public AaListParamsEqController(IAaListParamsEqService aaListParamsEqService) {
        this.aaListParamsEqService = aaListParamsEqService;
    }

    @RequestMapping("/list")
    public TableDataInfo list(AaListParamsEqVo aaListParamsEqVo) {
        startPage();
        List<AaListParamsEqVo> list = aaListParamsEqService.selectAaListParamsEqList(aaListParamsEqVo);
        return getDataTable(list);
    }

    @Log(title = "AA机台受控状态修改", businessType = BusinessType.UPDATE)
    @RequestMapping(value = "/ignore", method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult changeSwitch(@RequestBody AaListParamsEqVo aaListParamsEqVo) {
        int i = 0;
        Boolean b = aaListParamsEqService.isExist(aaListParamsEqVo);
        if (b) {
            i = aaListParamsEqService.editAaListParamsIgnoreEq(aaListParamsEqVo);
        } else {
            i = aaListParamsEqService.insertAaListParamsIgnoreEq(aaListParamsEqVo);
        }
        return toAjax(i);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public AjaxResult edit(@RequestBody AaListParamsEqVo aaListParamsEqVo) {
        int i = aaListParamsEqService.editAaListParamsEq(aaListParamsEqVo);
        return toAjax(i);
    }

    @Log(title = "AA List管控设备状态导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsEqVo aaListParamsEqVo) {
        List<AaListParamsEqVo> list = aaListParamsEqService.selectAaListParamsEqList(aaListParamsEqVo);
        ExcelUtil<AaListParamsEqVo> util = new ExcelUtil<>(AaListParamsEqVo.class);
        util.exportExcel(response, list, "AA List管控设备状态");
    }
}
