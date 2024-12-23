package com.qtech.im.aa.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.service.IAaListParamsStdModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 14:25:30
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/model/detail")
public class AaListParamsStdModelController extends BaseController {

    @Autowired
    private IAaListParamsStdModelService aaListParamsStdModelDetailService;

    @RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
    public TableDataInfo list(AaListParamsStdModel aaListParamsStdModelDetail) {
        startPage();
        List<AaListParamsStdModel> list = aaListParamsStdModelDetailService.selectList(aaListParamsStdModelDetail);
        return getDataTable(list);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public R<AaListParamsStdModel> getById(@PathVariable Long id) {
        AaListParamsStdModel one = aaListParamsStdModelDetailService.selectOne(id);
        return R.ok(one);
    }

    /**
     * 此Api用于AA List标准模版新增或者已存在模版更新请求处理
     */
    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
    public AjaxResult add(@RequestBody AaListParamsStdModel aaListParamsStdModelDetail) {
        return toAjax(aaListParamsStdModelDetailService.saveOrUpdateAaListParamsStdModel(aaListParamsStdModelDetail));
    }

    /**
     * 此Api用于AA List标准模版编辑请求处理
     */
    @RequestMapping(value = "/edit", produces = "application/json", method = RequestMethod.POST)
    public AjaxResult edit(@RequestBody AaListParamsStdModel aaListParamsStdModelDetail) {
        String nickName = getLoginUser().getUser().getNickName();
        aaListParamsStdModelDetail.setUpdateBy(nickName);
        aaListParamsStdModelDetail.setUpdateTime(DateUtils.getNowDate());
        return toAjax(aaListParamsStdModelDetailService.updateAaListParamsStdModel(aaListParamsStdModelDetail));
    }

    @RequestMapping(value = "/remove/{ids}", produces = "application/json", method = RequestMethod.DELETE)
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(aaListParamsStdModelDetailService.deleteAaListParamsStdModelByIds(Arrays.asList(ids)));
    }

    @Log(title = "AA-List参数标准模版明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsStdModel aaListParamsStdModelDetail) {
        List<AaListParamsStdModel> list = aaListParamsStdModelDetailService.selectList(aaListParamsStdModelDetail);
        ExcelUtil<AaListParamsStdModel> util = new ExcelUtil<AaListParamsStdModel>(AaListParamsStdModel.class);
        util.exportExcel(response, list, "AA-List参数标准模版明细");
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<AaListParamsStdModel> util = new ExcelUtil<>(AaListParamsStdModel.class);
        util.importTemplateExcel(response, "AA-List参数导入模版");
    }

    @PreAuthorize("@ss.hasPermi('aa:params:model:upload')")
    @Log(title = "AA参数模版导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload/manual")
    public AjaxResult uploadManual(MultipartFile file) throws Exception {
        ExcelUtil<AaListParamsStdModel> util = new ExcelUtil<>(AaListParamsStdModel.class);
        List<AaListParamsStdModel> paramsModelList = util.importExcel(file.getInputStream());

        String nickName = getLoginUser().getUser().getNickName();
        Date date = DateUtils.getNowDate();
        for (AaListParamsStdModel aaListParamsStdModelDetail : paramsModelList) {
            aaListParamsStdModelDetail.setCreateBy(nickName);
            aaListParamsStdModelDetail.setCreateTime(date);
        }
        Map<String, Object> resultMap = aaListParamsStdModelDetailService.uploadManual(paramsModelList);
        return resultMap.get("flag").equals(true) ? AjaxResult.success(resultMap.get("msg").toString()) : AjaxResult.warn(resultMap.get("msg").toString());
    }

    @PreAuthorize("@ss.hasPermi('aa:params:model:upload')")
    @Log(title = "AA参数模版导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload/online")
    public AjaxResult uploadOnline(@RequestBody AaListParamsStdModel aaListParamsStdModelDetail) throws Exception {
        if (aaListParamsStdModelDetail == null) {
            return AjaxResult.error("请求体不能为空");
        }

        Map<String, Object> resultMap = aaListParamsStdModelDetailService.uploadOnline(aaListParamsStdModelDetail);
        return resultMap.get("flag").equals(true) ? AjaxResult.success(resultMap.get("msg").toString()) : AjaxResult.warn(resultMap.get("msg").toString());
    }
}
