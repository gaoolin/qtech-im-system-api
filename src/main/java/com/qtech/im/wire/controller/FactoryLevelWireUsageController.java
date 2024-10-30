package com.qtech.im.wire.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wire.domain.FactoryLevelWireUsage;
import com.qtech.im.wire.service.IFactoryLevelWireUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 厂区级金线用量监控Controller
 *
 * @author qtech
 * @date 2023-05-17
 */
@RestController
@RequestMapping("/biz/wire/monitor/factory")
public class FactoryLevelWireUsageController extends BaseController {
    @Autowired
    private IFactoryLevelWireUsageService factoryLevelWireUsageService;

    /**
     * 查询厂区级金线用量监控列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(FactoryLevelWireUsage factoryLevelWireUsage) {
        startPage();
        List<FactoryLevelWireUsage> list = factoryLevelWireUsageService.selectFactoryLevelWireUsageList(factoryLevelWireUsage);
        return getDataTable(list);
    }

    /**
     * 导出厂区级金线用量监控列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:export')")
    @Log(title = "厂区级金线用量监控" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FactoryLevelWireUsage factoryLevelWireUsage) {
        List<FactoryLevelWireUsage> list = factoryLevelWireUsageService.selectFactoryLevelWireUsageList(factoryLevelWireUsage);
        ExcelUtil<FactoryLevelWireUsage> util = new ExcelUtil<FactoryLevelWireUsage>(FactoryLevelWireUsage.class);
        util.exportExcel(response, list, "厂区级金线用量监控数据");
    }

    /**
     * 获取厂区级金线用量监控详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:query')")
    @GetMapping(value = "/{factoryName}")
    public AjaxResult getInfo(@PathVariable("factoryName") String factoryName) {
        return success(factoryLevelWireUsageService.selectFactoryLevelWireUsageByFactoryName(factoryName));
    }

    /**
     * 新增厂区级金线用量监控
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:add')")
    @Log(title = "厂区级金线用量监控" , businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FactoryLevelWireUsage factoryLevelWireUsage) {
        return toAjax(factoryLevelWireUsageService.insertFactoryLevelWireUsage(factoryLevelWireUsage));
    }

    /**
     * 修改厂区级金线用量监控
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:edit')")
    @Log(title = "厂区级金线用量监控" , businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FactoryLevelWireUsage factoryLevelWireUsage) {
        return toAjax(factoryLevelWireUsageService.updateFactoryLevelWireUsage(factoryLevelWireUsage));
    }

    /**
     * 删除厂区级金线用量监控
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/monitor:factory:remove')")
    @Log(title = "厂区级金线用量监控" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{factoryNames}")
    public AjaxResult remove(@PathVariable String[] factoryNames) {
        return toAjax(factoryLevelWireUsageService.deleteFactoryLevelWireUsageByFactoryNames(factoryNames));
    }
}
