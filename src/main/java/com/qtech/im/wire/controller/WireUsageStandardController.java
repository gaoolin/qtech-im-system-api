package com.qtech.im.wire.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.SecurityUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wire.domain.WireUsageStandard;
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
@RequestMapping("/biz/wire/standard/query")
public class WireUsageStandardController extends BaseController {
    @Autowired
    private IWireUsageStandardService wireUsageStandardService;

    /**
     * 查询金线标准用量信息列表
     */

    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:list')")
    @GetMapping("/list")
    public TableDataInfo list(WireUsageStandard wireUsageStandard) {
        startPage();
        List<WireUsageStandard> list = wireUsageStandardService.selectWireUsageStandardList(wireUsageStandard);
        return getDataTable(list);
    }

    /**
     * 导出金线标准用量信息列表
     */

    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:export')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WireUsageStandard wireUsageStandard) {
        List<WireUsageStandard> list = wireUsageStandardService.selectWireUsageStandardList(wireUsageStandard);
        ExcelUtil<WireUsageStandard> util = new ExcelUtil<>(WireUsageStandard.class);
        util.exportExcel(response, list, "金线标准用量信息数据");
    }

    /**
     * 获取金线标准用量信息详细信息
     */

    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:query')")
    @GetMapping(value = "/{mcId}")
    public AjaxResult getInfo(@PathVariable("mcId") String mcId) {
        return success(wireUsageStandardService.selectWireUsageStandardByMcId(mcId));
    }

    /**
     * 新增金线标准用量信息
     */

    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:add')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WireUsageStandard wireUsageStandard) {
        wireUsageStandard.setCreateBy(SecurityUtils.getUsername());
        wireUsageStandard.setCreateTime(DateUtils.getNowDate());

        WireUsageStandard p = new WireUsageStandard();
        p.setMcId(wireUsageStandard.getMcId());
        List<WireUsageStandard> isExist = wireUsageStandardService.selectWireUsageStandardList(p);

        if (isExist.size() != 0) {
            return AjaxResult.warn("机型【" + wireUsageStandard.getMcId() + "】金线标准用量已存在！");
        }

        int code = wireUsageStandardService.insertWireUsageStandard(wireUsageStandard);
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
    public AjaxResult edit(@RequestBody WireUsageStandard wireUsageStandard) {
        wireUsageStandard.setUpdateBy(SecurityUtils.getUsername());
        wireUsageStandard.setCreateTime(DateUtils.getNowDate());

        int code = wireUsageStandardService.updateWireUsageStandard(wireUsageStandard);
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
    @DeleteMapping("/{mcId}")
    public AjaxResult remove(@PathVariable String mcId) {
        int code = wireUsageStandardService.deleteWireUsageStandardByMcId(mcId);
        // 由于Doris的删除目前是逻辑删除，因此对于这个值是恒为0；
        if (code == 0) {
            code = 1;
        } else {
            code = 0;
        }
        return toAjax(code);
    }
}
