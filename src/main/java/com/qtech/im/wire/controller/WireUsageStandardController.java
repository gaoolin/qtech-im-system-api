package com.qtech.im.wire.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.SecurityUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wire.domain.ImWireUsageStandard;
import com.qtech.im.wire.service.IWireUsageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 金线标准用量信息Controller
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@RestController
@RequestMapping("/biz/wire/standard")
public class WireUsageStandardController extends BaseController {
    @Autowired
    private IWireUsageStandardService wireUsageStandardService;

    /**
     * 查询金线标准用量信息列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:list')")
    @GetMapping("/list")
    public TableDataInfo list(ImWireUsageStandard imWireUsageStandard) {
        startPage();
        List<ImWireUsageStandard> list = wireUsageStandardService.selectWireUsageStandardList(imWireUsageStandard);
        return getDataTable(list);
    }

    /**
     * 导出金线标准用量信息列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:export')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImWireUsageStandard imWireUsageStandard) {
        List<ImWireUsageStandard> list = wireUsageStandardService.selectWireUsageStandardList(imWireUsageStandard);
        ExcelUtil<ImWireUsageStandard> util = new ExcelUtil<>(ImWireUsageStandard.class);
        util.exportExcel(response, list, "金线标准用量信息数据");
    }

    /**
     * 获取金线标准用量信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:query')")
    @GetMapping(value = "/{prodType}")
    public AjaxResult getInfo(@PathVariable("prodType") String prodType) {
        return success(wireUsageStandardService.selectWireUsageStandardByProdType(prodType));
    }

    /**
     * 新增金线标准用量信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:add')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ImWireUsageStandard imWireUsageStandard) {
        imWireUsageStandard.setCreateBy(SecurityUtils.getUsername());
        imWireUsageStandard.setCreateTime(DateUtils.getNowDate());

        ImWireUsageStandard p = new ImWireUsageStandard();
        p.setMcId(imWireUsageStandard.getMcId());
        List<ImWireUsageStandard> isExist = wireUsageStandardService.selectWireUsageStandardList(p);

        if (!isExist.isEmpty()) {
            return AjaxResult.warn("机型【" + imWireUsageStandard.getMcId() + "】金线标准用量已存在！");
        }

        int code = wireUsageStandardService.insertWireUsageStandard(imWireUsageStandard);
        if (code == -1) {
            code = 1;
        }
        return toAjax(code);
    }

    /**
     * 修改金线标准用量信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:edit')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ImWireUsageStandard imWireUsageStandard) {
        imWireUsageStandard.setUpdateBy(getLoginUser().getUsername());
        imWireUsageStandard.setCreateTime(DateUtils.getNowDate());

        int code = wireUsageStandardService.updateWireUsageStandard(imWireUsageStandard);
        if (code == -1) {
            code = 1;
        }
        return toAjax(code);
    }

    /**
     * 删除金线标准用量信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:remove')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{prodType}")
    public AjaxResult remove(@PathVariable String prodType) {
        int code = wireUsageStandardService.deleteWireUsageStandardByProdType(prodType);
        // 由于Doris的删除目前是逻辑删除，因此对于这个值是恒为0；
        if (code == 0) {
            code = 1;
        } else {
            code = 0;
        }
        return toAjax(code);
    }
}
