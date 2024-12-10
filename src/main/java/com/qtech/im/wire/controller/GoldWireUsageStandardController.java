package com.qtech.im.wire.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImStandardGoldWireUsage;
import com.qtech.im.wire.service.IGoldWireUsageStandardService;
import com.qtech.im.wire.vo.ImStandardGoldWireUsageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 金线标准用量信息Controller
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@RestController
@RequestMapping("/biz/wire/standard")
public class GoldWireUsageStandardController extends BaseController {
    @Autowired
    private IGoldWireUsageStandardService wireUsageStandardService;

    /**
     * 查询金线标准用量信息列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:list')")
    @GetMapping("/list")
    public TableDataInfo list(ImStandardGoldWireUsage imWireUsageStandard) {
        startPage();
        QtechImVoUtil.QtechImVos<ImStandardGoldWireUsageVo> list = wireUsageStandardService.list(imWireUsageStandard);
        return QtechImVoUtil.getVoDataTable(list);
    }

    /**
     * 导出金线标准用量信息列表
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:export')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImStandardGoldWireUsage imWireUsageStandard) {
        QtechImVoUtil.QtechImVos<ImStandardGoldWireUsageVo> list = wireUsageStandardService.list(imWireUsageStandard);
        ExcelUtil<ImStandardGoldWireUsageVo> util = new ExcelUtil<>(ImStandardGoldWireUsageVo.class);
        util.exportExcel(response, list.getData(), "金线标准用量信息数据");
    }

    /**
     * 获取金线标准用量信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:query')")
    @GetMapping(value = "/{prodType}")
    public AjaxResult getByProdType(@PathVariable("prodType") String prodType) {
        return success(wireUsageStandardService.getByProdType(prodType));
    }

    /**
     * 新增金线标准用量信息
     */
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:query:add')")
    @Log(title = "金线标准用量信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ImStandardGoldWireUsageVo imStandardGoldWireUsageVo) {
        imStandardGoldWireUsageVo.setCreateBy(getLoginUser().getUser().getNickName());
        imStandardGoldWireUsageVo.setCreateTime(DateUtils.getNowDate());

        ImStandardGoldWireUsage one = wireUsageStandardService.getByProdType(imStandardGoldWireUsageVo.getProdType());

        if (one != null) {
            return AjaxResult.warn("机型【" + imStandardGoldWireUsageVo.getProdType() + "】金线标准用量已存在！");
        }

        int code = wireUsageStandardService.addOne(imStandardGoldWireUsageVo);
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
    public AjaxResult edit(@RequestBody ImStandardGoldWireUsage imWireUsageStandard) {
        imWireUsageStandard.setUpdateBy(getLoginUser().getUsername());
        imWireUsageStandard.setCreateTime(DateUtils.getNowDate());

        int code = wireUsageStandardService.update(imWireUsageStandard);
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
        int code = wireUsageStandardService.remove(prodType);
        // 由于Doris的删除目前是逻辑删除，因此对于这个值是恒为0；
        if (code == 0) {
            code = 1;
        } else {
            code = 0;
        }
        return toAjax(code);
    }
}