package com.qtech.im.fixture.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureParamsAa;
import com.qtech.im.fixture.service.IFixtureParamsAaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/02 13:09:33
 * desc   :
 */

@RestController
@RequestMapping("/fixture/params/aa")
public class FixtureParamsAaController extends BaseController {

    @Autowired
    IFixtureParamsAaService fixtureParamsAaService;

    @GetMapping("/list")
    public TableDataInfo list(FixtureParamsAa fixtureParamsAa) {
        startPage();
        return getDataTable(fixtureParamsAaService.selectFixtureParamsAaList(fixtureParamsAa));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        FixtureParamsAa fixtureParamsAa = new FixtureParamsAa();
        fixtureParamsAa.setId(id);
        return success(fixtureParamsAaService.selectOneFixtureParamsAa(fixtureParamsAa));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:ops')")
    @PostMapping
    public AjaxResult add(@RequestBody FixtureParamsAa fixtureParamsAa) {
        return toAjax(fixtureParamsAaService.addFixtureParamsAa(fixtureParamsAa));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:upload')")
    @Log(title = "AA共治具导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws Exception {
        ExcelUtil<FixtureParamsAa> util = new ExcelUtil<>(FixtureParamsAa.class);
        List<FixtureParamsAa> fixtureParamsAas = util.importExcel(file.getInputStream());
        Map<String, String> resultMap = fixtureParamsAaService.uploadFixtureParamsAa(fixtureParamsAas);

        return "1".equals(resultMap.get("flag")) ? AjaxResult.success(resultMap.get("result")) : AjaxResult.warn(resultMap.get("result"));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<FixtureParamsAa> util = new ExcelUtil<>(FixtureParamsAa.class);
        util.importTemplateExcel(response, "AA治具参数导入模板");
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:ops')")
    @Log(title = "AA共治具", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FixtureParamsAa fixtureParamsAa) {
        return toAjax(fixtureParamsAaService.editFixtureParamsAa(fixtureParamsAa));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:ops')")
    @Log(title = "AA共治具", businessType = BusinessType.DELETE)
    @DeleteMapping
    public AjaxResult remove(@RequestBody FixtureParamsAa fixtureParamsAa) {
        return toAjax(fixtureParamsAaService.removeFixtureParamsAa(fixtureParamsAa));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:export')")
    @Log(title = "AA治具因子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FixtureParamsAa fixtureParamsAa) {
        List<FixtureParamsAa> list = fixtureParamsAaService.selectFixtureParamsAaList(fixtureParamsAa);
        ExcelUtil<FixtureParamsAa> util = new ExcelUtil<FixtureParamsAa>(FixtureParamsAa.class);
        util.exportExcel(response, list, "AA治具因子");
    }
}
