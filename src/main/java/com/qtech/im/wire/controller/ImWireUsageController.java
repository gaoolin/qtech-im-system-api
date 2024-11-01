package com.qtech.im.wire.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wire.domain.ImWireUsage;
import com.qtech.im.wire.service.IImWireUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:19:29
 * desc   :
 */

@RestController
@RequestMapping("/biz/wire/monitor")
public class ImWireUsageController extends BaseController {

    @Autowired
    private IImWireUsageService wireUsageService;

    @GetMapping("/eq/list")
    public TableDataInfo eqWireList(ImWireUsage imWireUsage) {
        startPage();
        List<ImWireUsage> list = wireUsageService.selectEqLevelWireUsageList(imWireUsage);
        return getDataTable(list);
    }

    @Log(title = "设备金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/eq/export")
    public void eqExport(HttpServletResponse response, ImWireUsage imWireUsage) {
        List<ImWireUsage> list = wireUsageService.selectEqLevelWireUsageList(imWireUsage);
        ExcelUtil<ImWireUsage> util = new ExcelUtil<ImWireUsage>(ImWireUsage.class);
        util.exportExcel(response, list, "设备金线消耗量数据");
    }

    @GetMapping("/group/list")
    public TableDataInfo groupWireList(ImWireUsage imWireUsage) {
        startPage();
        List<ImWireUsage> list = wireUsageService.selectGroupLevelWireUsageList(imWireUsage);
        return getDataTable(list);
    }

    @Log(title = "车间金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/group/export")
    public void groupWireExport(HttpServletResponse response, ImWireUsage imWireUsage) {
        List<ImWireUsage> list = wireUsageService.selectGroupLevelWireUsageList(imWireUsage);
        ExcelUtil<ImWireUsage> util = new ExcelUtil<ImWireUsage>(ImWireUsage.class);
        util.exportExcel(response, list, "车间金线消耗量数据");
    }

    @GetMapping("/factory/list")
    public TableDataInfo factoryWireList(ImWireUsage eqLevelWireUsage) {
        startPage();
        List<ImWireUsage> list = wireUsageService.selectFactoryLevelWireUsageList(eqLevelWireUsage);
        return getDataTable(list);
    }

    @Log(title = "厂区金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/factory/export")
    public void factoryWireExport(HttpServletResponse response, ImWireUsage eqLevelWireUsage) {
        List<ImWireUsage> list = wireUsageService.selectFactoryLevelWireUsageList(eqLevelWireUsage);
        ExcelUtil<ImWireUsage> util = new ExcelUtil<ImWireUsage>(ImWireUsage.class);
        util.exportExcel(response, list, "厂区金线消耗量数据");
    }
}
