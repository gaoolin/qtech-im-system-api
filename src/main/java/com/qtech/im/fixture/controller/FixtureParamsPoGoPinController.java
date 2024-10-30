package com.qtech.im.fixture.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureParamsPoGoPin;
import com.qtech.im.fixture.service.IFixtureParamsPoGoPinService;
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
 * date   :  2024/03/07 15:30:06
 * desc   :
 */

@RestController
@RequestMapping("/fixture/params/pogopin")
public class FixtureParamsPoGoPinController extends BaseController {

    @Autowired
    IFixtureParamsPoGoPinService fixtureFactorPoGoPinService;

    @GetMapping("/list")
    public TableDataInfo list(FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        startPage();
        List<FixtureParamsPoGoPin> list = fixtureFactorPoGoPinService.selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin);
        return getDataTable(list);
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(fixtureFactorPoGoPinService.selectOneFixtureParamsPoGoPin(id));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:pogopin:ops')")
    @PutMapping
    public AjaxResult edit(@RequestBody FixtureParamsPoGoPin FixtureParamsPoGoPin)
    {
        return toAjax(fixtureFactorPoGoPinService.editFixtureParamsPoGoPin(FixtureParamsPoGoPin));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:pogopin:ops')")
    @PostMapping
    public AjaxResult add(@RequestBody FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        return toAjax(fixtureFactorPoGoPinService.addFixtureParamsPoGoPin(FixtureParamsPoGoPin));
    }
    @PreAuthorize("@ss.hasPermi('fixture:params:pogopin:ops')")
    @DeleteMapping
    public AjaxResult remove(@RequestBody FixtureParamsPoGoPin FixtureParamsPoGoPin) {
        return toAjax(fixtureFactorPoGoPinService.removeFixtureParamsPoGoPin(FixtureParamsPoGoPin));
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:pogopin:upload')")
    @Log(title = "pogopin共治具导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws Exception {
        ExcelUtil<FixtureParamsPoGoPin> util = new ExcelUtil<>(FixtureParamsPoGoPin.class);
        List<FixtureParamsPoGoPin> FixtureParamsPoGoPins = util.importExcel(file.getInputStream());
        Map<String, String> resultMap = fixtureFactorPoGoPinService.uploadFixtureParamsPoGoPin(FixtureParamsPoGoPins);

        return "1".equals(resultMap.get("flag")) ? AjaxResult.success(resultMap.get("result")) : AjaxResult.warn(resultMap.get("result"));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<FixtureParamsPoGoPin> util = new ExcelUtil<>(FixtureParamsPoGoPin.class);
        util.importTemplateExcel(response, "PoGoPin治具因子导入模板");
    }

    /**
     * 导出治具列表fixture:match:pogopin
     */
    @PreAuthorize("@ss.hasPermi('fixture:params:pogopin:export')")
    @Log(title = "pogopin治具因子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FixtureParamsPoGoPin FixtureParamsPoGoPin)
    {
        List<FixtureParamsPoGoPin> list = fixtureFactorPoGoPinService.selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin);
        ExcelUtil<FixtureParamsPoGoPin> util = new ExcelUtil<FixtureParamsPoGoPin>(FixtureParamsPoGoPin.class);
        util.exportExcel(response, list, "PoGoPin治具因子");
    }
}
