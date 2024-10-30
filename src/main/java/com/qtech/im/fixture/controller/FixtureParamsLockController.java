package com.qtech.im.fixture.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureParamsLock;
import com.qtech.im.fixture.service.IFixtureParamsLockService;
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
 * date   :  2024/04/16 14:13:41
 * desc   :
 */

@RestController
@RequestMapping("/fixture/params/lock")
public class FixtureParamsLockController extends BaseController {

    @Autowired
    private IFixtureParamsLockService fixtureParamsLockService;

    @GetMapping("/list")
    public TableDataInfo list(FixtureParamsLock fixtureParamsLock) {
        startPage();
        return getDataTable(fixtureParamsLockService.selectFixtureParamsLockList(fixtureParamsLock));
    }

    @PostMapping
    public AjaxResult add(@RequestBody FixtureParamsLock fixtureParamsLock) {
        return toAjax(fixtureParamsLockService.addFixtureParamsLock(fixtureParamsLock));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody FixtureParamsLock fixtureParamsLock) {
        return toAjax(fixtureParamsLockService.editFixtureParamsLock(fixtureParamsLock));
    }

    @DeleteMapping
    public AjaxResult remove(@RequestBody FixtureParamsLock fixtureParamsLock) {
        return toAjax(fixtureParamsLockService.removeFixtureParamsLock(fixtureParamsLock));
    }

    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        FixtureParamsLock fixtureParamsLock = new FixtureParamsLock();
        fixtureParamsLock.setId(id);
        return success(fixtureParamsLockService.selectOneFixtureParamsLock(fixtureParamsLock));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<FixtureParamsLock> util = new ExcelUtil<>(FixtureParamsLock.class);
        util.importTemplateExcel(response, "锁附治具参数导入模板");
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:lock:export')")
    @Log(title = "锁附治具因子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FixtureParamsLock fixtureParamsLock) {
        List<FixtureParamsLock> list = fixtureParamsLockService.selectFixtureParamsLockList(fixtureParamsLock);
        ExcelUtil<FixtureParamsLock> util = new ExcelUtil<FixtureParamsLock>(FixtureParamsLock.class);
        util.exportExcel(response, list, "锁附治具因子");
    }

    @PreAuthorize("@ss.hasPermi('fixture:params:aa:upload')")
    @Log(title = "AA共治具导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws Exception {
        ExcelUtil<FixtureParamsLock> util = new ExcelUtil<>(FixtureParamsLock.class);
        List<FixtureParamsLock> fixtureParamsLocks = util.importExcel(file.getInputStream());
        Map<String, String> resultMap = fixtureParamsLockService.uploadFixtureParamsLock(fixtureParamsLocks);

        return "1".equals(resultMap.get("flag")) ? AjaxResult.success(resultMap.get("result")) : AjaxResult.warn(resultMap.get("result"));
    }
}
