package com.qtech.im.wb.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wb.domain.WbOlpStdModDetail;
import com.qtech.im.wb.service.IWbOlpStdModelDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 标准模版明细Controller
 *
 * @author gaozhilin
 * @date 2023-09-06
 */
@RestController
@RequestMapping("/wb/olp/detail")
public class WbOlpStdModDetailController extends BaseController
{
    @Autowired
    private IWbOlpStdModelDetailService wbOlpStdModelDetailService;

    /**
     * 查询标准模版明细列表
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(WbOlpStdModDetail wbOlpStdModDetail)
    {
        startPage();
        List<WbOlpStdModDetail> list = wbOlpStdModelDetailService.selectWbOlpStdModDetailList(wbOlpStdModDetail);
        return getDataTable(list);
    }

    /**
     * 导出标准模版明细列表
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:export')")
    @Log(title = "标准模版明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WbOlpStdModDetail wbOlpStdModDetail)
    {
        List<WbOlpStdModDetail> list = wbOlpStdModelDetailService.selectWbOlpStdModDetailList(wbOlpStdModDetail);
        ExcelUtil<WbOlpStdModDetail> util = new ExcelUtil<WbOlpStdModDetail>(WbOlpStdModDetail.class);
        util.exportExcel(response, list, "标准模版明细数据");
    }

    /**
     * 获取标准模版明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wbOlpStdModelDetailService.selectWbOlpStdModDetailById(id));
    }

    /**
     * 新增标准模版明细
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:add')")
    @Log(title = "标准模版明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WbOlpStdModDetail wbOlpStdModDetail)
    {
        return toAjax(wbOlpStdModelDetailService.insertWbOlpStdModDetail(wbOlpStdModDetail));
    }

    /**
     * 修改标准模版明细
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:edit')")
    @Log(title = "标准模版明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WbOlpStdModDetail wbOlpStdModDetail)
    {
        return toAjax(wbOlpStdModelDetailService.updateWbOlpStdModDetail(wbOlpStdModDetail));
    }

    /**
     * 删除标准模版明细
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:detail:remove')")
    @Log(title = "标准模版明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wbOlpStdModelDetailService.deleteWbOlpStdModDetailByIds(ids));
    }
}
