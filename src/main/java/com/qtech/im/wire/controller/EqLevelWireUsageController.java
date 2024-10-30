package com.qtech.im.wire.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wire.domain.EqLevelWireUsage;
import com.qtech.im.wire.service.IEqLevelWireUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 金线消耗量Controller
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@RestController
@RequestMapping("/biz/wire/monitor/eq")
public class EqLevelWireUsageController extends BaseController
{
    @Autowired
    private IEqLevelWireUsageService wireUsageService;

    /**
     * 查询金线消耗量列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:eq:list')")
    @GetMapping("/list")
    public TableDataInfo list(EqLevelWireUsage eqLevelWireUsage)
    {
        startPage();
        List<EqLevelWireUsage> list = wireUsageService.selectEqLevelWireUsageList(eqLevelWireUsage);
        return getDataTable(list);
    }

    /**
     * 导出金线消耗量列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:eq:export')")
    @Log(title = "金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EqLevelWireUsage eqLevelWireUsage)
    {
        List<EqLevelWireUsage> list = wireUsageService.selectEqLevelWireUsageList(eqLevelWireUsage);
        ExcelUtil<EqLevelWireUsage> util = new ExcelUtil<EqLevelWireUsage>(EqLevelWireUsage.class);
        util.exportExcel(response, list, "金线消耗量数据");
    }
}
