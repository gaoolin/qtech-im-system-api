package com.qtech.im.aa.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.ImAaListParamsStdModelDetail;
import com.qtech.im.aa.service.ImAaListParamsStdModelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 14:25:30
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/model/detail")
public class AaListParamsStdModelDetailController extends BaseController {

    @Autowired
    private ImAaListParamsStdModelDetailService aaListParamsStdModelDetailService;

    @RequestMapping(value = "/list" , produces = "application/json" , method = RequestMethod.GET)
    public TableDataInfo list(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        startPage();
        List<ImAaListParamsStdModelDetail> list = aaListParamsStdModelDetailService.selectAaListParamsStdModelList(aaListParamsStdModelDetail);
        return getDataTable(list);
    }

    @RequestMapping(value = "/{id}" , produces = "application/json" , method = RequestMethod.GET)
    public TableDataInfo getInfoById(@PathVariable Long id) {
        ImAaListParamsStdModelDetail param = new ImAaListParamsStdModelDetail();
        param.setId(id);
        ImAaListParamsStdModelDetail one = aaListParamsStdModelDetailService.selectOne(param);
        return getDataTable(Collections.singletonList(one));
    }

    @RequestMapping(value = "/add" , produces = "application/json" , method = RequestMethod.POST)
    public AjaxResult add(@RequestBody ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {

        return toAjax(aaListParamsStdModelDetailService.insertAaListParamsStdModel(aaListParamsStdModelDetail));
    }

    @RequestMapping(value = "/edit" , produces = "application/json" , method = RequestMethod.POST)
    public AjaxResult edit(@RequestBody ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        String nickName = getLoginUser().getUser().getNickName();
        aaListParamsStdModelDetail.setUpdateBy(nickName);
        aaListParamsStdModelDetail.setUpdateTime(DateUtils.getNowDate());
        return toAjax(aaListParamsStdModelDetailService.updateAaListParamsStdModel(aaListParamsStdModelDetail));
    }

    @RequestMapping(value = "/remove/{ids}" , produces = "application/json" , method = RequestMethod.DELETE)
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(aaListParamsStdModelDetailService.deleteAaListParamsStdModelByIds(Arrays.asList(ids)));
    }

    @Log(title = "AA-List参数标准模版明细" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        List<ImAaListParamsStdModelDetail> list = aaListParamsStdModelDetailService.selectAaListParamsStdModelList(aaListParamsStdModelDetail);
        ExcelUtil<ImAaListParamsStdModelDetail> util = new ExcelUtil<ImAaListParamsStdModelDetail>(ImAaListParamsStdModelDetail.class);
        util.exportExcel(response, list, "AA-List参数标准模版明细");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<ImAaListParamsStdModelDetail> util = new ExcelUtil<>(ImAaListParamsStdModelDetail.class);
        util.importTemplateExcel(response, "AA-List参数导入模版");
    }

    @PreAuthorize("@ss.hasPermi('aa:params:model:upload')")
    @Log(title = "AA参数模版导入" , businessType = BusinessType.IMPORT)
    @PostMapping("/upload/manual")
    public AjaxResult uploadManual(MultipartFile file) throws Exception {
        ExcelUtil<ImAaListParamsStdModelDetail> util = new ExcelUtil<>(ImAaListParamsStdModelDetail.class);
        List<ImAaListParamsStdModelDetail> paramsModelList = util.importExcel(file.getInputStream());

        String nickName = getLoginUser().getUser().getNickName();
        Date date = DateUtils.getNowDate();
        for (ImAaListParamsStdModelDetail aaListParamsStdModelDetail : paramsModelList) {
            aaListParamsStdModelDetail.setCreateBy(nickName);
            aaListParamsStdModelDetail.setCreateTime(date);
        }
        Map<String, Object> resultMap = aaListParamsStdModelDetailService.uploadManual(paramsModelList);
        return resultMap.get("flag").equals(true) ? AjaxResult.success(resultMap.get("msg").toString()) : AjaxResult.warn(resultMap.get("msg").toString());
    }

    @PreAuthorize("@ss.hasPermi('aa:params:model:upload')")
    @Log(title = "AA参数模版导入" , businessType = BusinessType.IMPORT)
    @PostMapping("/upload/online")
    public AjaxResult uploadOnline(@RequestBody ImAaListParamsStdModelDetail aaListParamsStdModelDetail) throws Exception {
        Map<String, Object> resultMap = aaListParamsStdModelDetailService.uploadOnline(aaListParamsStdModelDetail);
        return resultMap.get("flag").equals(true) ? AjaxResult.success(resultMap.get("msg").toString()) : AjaxResult.warn(resultMap.get("msg").toString());
    }
}
