package com.qtech.im.aa.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.SecurityUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.service.IAaListParamsStdTemplateInfoService;
import com.qtech.im.aa.vo.AaListParamsStdModelInfoVo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:35:31
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/model/info")
public class AaListParamsStdTemplateInfoController extends BaseController {
    @Autowired
    private IAaListParamsStdTemplateInfoService aaListParamsStdModelInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        startPage();
        QtechImVoUtil.QtechImVos<AaListParamsStdModelInfoVo> list = aaListParamsStdModelInfoService.selectStdModelInfoList(aaListParamsStdTemplateInfo);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @RequestMapping(value = "/edit", produces = "application/json", method = RequestMethod.POST)
    public AjaxResult edit(@RequestBody AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        aaListParamsStdTemplateInfo.setUpdateBy(sysUserService.selectUserByUserName(SecurityUtils.getUsername()).getNickName());
        aaListParamsStdTemplateInfo.setUpdateTime(DateUtils.getNowDate());
        return toAjax(aaListParamsStdModelInfoService.updateStdModelInfo(aaListParamsStdTemplateInfo));
    }

    @RequestMapping(value = "/remove/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(aaListParamsStdModelInfoService.deleteStdModelInfoById(id));
    }

    @Log(title = "AA-List参数标准模版信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsStdTemplateInfo aaListParamsStdTemplateInfo) {
        QtechImVoUtil.QtechImVos<AaListParamsStdModelInfoVo> list = aaListParamsStdModelInfoService.selectStdModelInfoList(aaListParamsStdTemplateInfo);
        ExcelUtil<AaListParamsStdModelInfoVo> util = new ExcelUtil<AaListParamsStdModelInfoVo>(AaListParamsStdModelInfoVo.class);
        util.exportExcel(response, list.getData(), "AA-List参数标准模版信息");
    }
}