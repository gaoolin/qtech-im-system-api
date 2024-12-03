package com.qtech.im.aa.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.SecurityUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:35:31
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/model/info")
public class AaListParamsStdModelInfoController extends BaseController {

    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/list" , produces = "application/json" , method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        startPage();
        List<AaListParamsStdModelInfoVo> list = aaListParamsStdModelInfoService.selectStdModelInfoList(aaListParamsStdModelInfoVo);
        return getDataTable(list);
    }

    @RequestMapping(value = "/edit" , produces = "application/json" , method = RequestMethod.POST)
    public AjaxResult edit(@RequestBody AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        aaListParamsStdModelInfoVo.setUpdateBy(sysUserService.selectUserByUserName(SecurityUtils.getUsername()).getNickName());
        aaListParamsStdModelInfoVo.setUpdateTime(DateUtils.getNowDate());
        return toAjax(aaListParamsStdModelInfoService.updateStdModelInfo(aaListParamsStdModelInfoVo));
    }

    @RequestMapping(value = "/remove/{id}" , produces = "application/json" , method = RequestMethod.DELETE)
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(aaListParamsStdModelInfoService.deleteStdModelInfoByIds(Collections.singletonList(id).toArray(new Long[0])));
    }

    @Log(title = "AA-List参数标准模版信息" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        List<AaListParamsStdModelInfoVo> list = aaListParamsStdModelInfoService.selectStdModelInfoList(aaListParamsStdModelInfoVo);
        ExcelUtil<AaListParamsStdModelInfoVo> util = new ExcelUtil<AaListParamsStdModelInfoVo>(AaListParamsStdModelInfoVo.class);
        util.exportExcel(response, list, "AA-List参数标准模版信息");
    }
}
