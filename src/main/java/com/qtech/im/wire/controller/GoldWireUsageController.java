package com.qtech.im.wire.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import com.qtech.im.wire.service.IGoldWireUsageService;
import com.qtech.im.wire.vo.ImGoldWireUsageVo;
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
public class GoldWireUsageController extends BaseController {

    @Autowired
    private IGoldWireUsageService wireUsageService;

    @GetMapping("/eq/list")
    public TableDataInfo eqWireList(ImGoldWireUsage imGoldWireUsage) {
        startPage();
        QtechImVoUtil.QtechImVos<ImGoldWireUsageVo> list = wireUsageService.selectEqLevelWireUsageList(imGoldWireUsage);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @Log(title = "设备金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/eq/export")
    public void eqExport(HttpServletResponse response, ImGoldWireUsage imGoldWireUsage) {
        QtechImVoUtil.QtechImVos<ImGoldWireUsageVo> list = wireUsageService.selectEqLevelWireUsageList(imGoldWireUsage);
        ExcelUtil<ImGoldWireUsageVo> util = new ExcelUtil<ImGoldWireUsageVo>(ImGoldWireUsageVo.class);
        util.exportExcel(response, list.getData(), "设备金线消耗量数据");
    }

    @GetMapping("/group/list")
    public TableDataInfo groupWireList(ImGoldWireUsage imGoldWireUsage) {
        startPage();
        List<ImGoldWireUsage> list = wireUsageService.selectGroupLevelWireUsageList(imGoldWireUsage);
        return getDataTable(list);
    }

    @Log(title = "车间金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/group/export")
    public void groupWireExport(HttpServletResponse response, ImGoldWireUsage imGoldWireUsage) {
        List<ImGoldWireUsage> list = wireUsageService.selectGroupLevelWireUsageList(imGoldWireUsage);
        ExcelUtil<ImGoldWireUsage> util = new ExcelUtil<ImGoldWireUsage>(ImGoldWireUsage.class);
        util.exportExcel(response, list, "车间金线消耗量数据");
    }

    @GetMapping("/factory/list")
    public TableDataInfo factoryWireList(ImGoldWireUsage eqLevelWireUsage) {
        startPage();
        List<ImGoldWireUsage> list = wireUsageService.selectFactoryLevelWireUsageList(eqLevelWireUsage);
        return getDataTable(list);
    }

    @Log(title = "厂区金线消耗量", businessType = BusinessType.EXPORT)
    @PostMapping("/factory/export")
    public void factoryWireExport(HttpServletResponse response, ImGoldWireUsage eqLevelWireUsage) {
        List<ImGoldWireUsage> list = wireUsageService.selectFactoryLevelWireUsageList(eqLevelWireUsage);
        ExcelUtil<ImGoldWireUsage> util = new ExcelUtil<ImGoldWireUsage>(ImGoldWireUsage.class);
        util.exportExcel(response, list, "厂区金线消耗量数据");
    }
}
