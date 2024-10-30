package com.qtech.im.wb.controller;

import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wb.domain.WbOlpStdModInfo;
import com.qtech.im.wb.service.IWbOlpStdModelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 智慧打线图Controller
 *
 * @author gaozhilin
 * @date 2023-09-05
 */
@RestController
@RequestMapping("/wb/olp/info")
public class WbOlpStdModInfoController extends BaseController {
    @Autowired
    private IWbOlpStdModelInfoService wbOlpStdModelInfoService;

    /**
     * 查询智慧打线图列表
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(WbOlpStdModInfo wbOlpStdModInfo) {
        startPage();
        List<WbOlpStdModInfo> list = wbOlpStdModelInfoService.selectWbOlpStdModInfoList(wbOlpStdModInfo);
        return getDataTable(list);
    }

    /**
     * 导出智慧打线图列表
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:export')")
    @Log(title = "智慧打线图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WbOlpStdModInfo wbOlpStdModInfo) {
        List<WbOlpStdModInfo> list = wbOlpStdModelInfoService.selectWbOlpStdModInfoList(wbOlpStdModInfo);
        ExcelUtil<WbOlpStdModInfo> util = new ExcelUtil<WbOlpStdModInfo>(WbOlpStdModInfo.class);
        util.exportExcel(response, list, "智慧打线图数据");
    }

    /**
     * 获取智慧打线图详细信息
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid) {
        return success(wbOlpStdModelInfoService.selectWbOlpStdModInfoBySid(sid));
    }

    /**
     * 新增智慧打线图
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:add')")
    @Log(title = "智慧打线图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WbOlpStdModInfo wbOlpStdModInfo) {
        String nickName = getLoginUser().getUser().getNickName();
        wbOlpStdModInfo.setCreateBy(nickName);
        wbOlpStdModInfo.setCreateTime(DateUtils.getNowDate());
        return toAjax(wbOlpStdModelInfoService.insertWbOlpStdModInfo(wbOlpStdModInfo));
    }

    /**
     * 修改智慧打线图
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:edit')")
    @Log(title = "智慧打线图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WbOlpStdModInfo wbOlpStdModInfo) {
        String nickName = getLoginUser().getUser().getNickName();
        wbOlpStdModInfo.setUpdateBy(nickName);
        wbOlpStdModInfo.setUpdateTime(DateUtils.getNowDate());
        return toAjax(wbOlpStdModelInfoService.updateWbOlpStdModInfo(wbOlpStdModInfo));
    }

    /**
     * 删除智慧打线图
     */
    @PreAuthorize("@ss.hasPermi('wbOlp:info:remove')")
    @Log(title = "智慧打线图", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids) {
        return toAjax(wbOlpStdModelInfoService.deleteWbOlpStdModInfoBySids(sids));
    }
}
