package com.qtech.im.fixture.controller;

import com.qtech.common.utils.SecurityUtils;
import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.security.LoginUser;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureSharedInfo;
import com.qtech.im.fixture.service.IFixtureSharedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 治具Controller
 *
 * @author gaozhilin
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/fixture/search")
public class FixtureSharedInfoController extends BaseController
{
    @Autowired
    private IFixtureSharedInfoService fixtureSharedInfoService;

    /**
     * 治具共享信息查询
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(fixtureSharedInfoService.selectFixtureSharedInfoById(id));
    }

    /**
     * 查询治具列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FixtureSharedInfo fixtureSharedInfo)
    {
        startPage();
        List<FixtureSharedInfo> list = fixtureSharedInfoService.selectFixtureSharedInfoList(fixtureSharedInfo);
        return getDataTable(list);
    }

    /**
     * 导出治具列表
     */
    @Log(title = "治具", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FixtureSharedInfo fixtureSharedInfo)
    {
        List<FixtureSharedInfo> list = fixtureSharedInfoService.selectFixtureSharedInfoList(fixtureSharedInfo);
        ExcelUtil<FixtureSharedInfo> util = new ExcelUtil<FixtureSharedInfo>(FixtureSharedInfo.class);
        util.exportExcel(response, list, "共治具信息");
    }

    /**
     * 获取治具详细信息
     */
    @GetMapping(value = "/one")
    public AjaxResult getInfo(FixtureSharedInfo fixtureSharedInfo)
    {
        return success(fixtureSharedInfoService.selectFixtureSharedInfoOne(fixtureSharedInfo));
    }

    /**
     * 新增治具
     */

    @PreAuthorize("@ss.hasPermi('fixture:search:ops')")
    @Log(title = "治具", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FixtureSharedInfo fixtureSharedInfo)
    {
        return toAjax(fixtureSharedInfoService.addFixtureSharedInfo(fixtureSharedInfo));
    }

    /**
     * 修改治具
     */
    @PreAuthorize("@ss.hasPermi('fixture:search:ops')")
    @Log(title = "治具", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FixtureSharedInfo fixtureSharedInfo)
    {
        return toAjax(fixtureSharedInfoService.editFixtureSharedInfo(fixtureSharedInfo));
    }

    /**
     * 删除治具
     * 1、只传料号过来，则在共享信息表中删除料号对应的所有信息，删除料号表信息，检查机型表
     * 2、传参包括料号和机型，则只删除机型，同时判断是否需要删除料号
     */
    @PreAuthorize("@ss.hasPermi('fixture:search:ops')")
    @Log(title = "pogopin治具共用", businessType = BusinessType.DELETE)
	@DeleteMapping
    public AjaxResult remove(@RequestBody FixtureSharedInfo fixtureSharedInfo)
    {
        return toAjax(fixtureSharedInfoService.deleteFixtureSharedInfo(fixtureSharedInfo));
    }

    @GetMapping("/deptIds")
    public R<List<String>> getDeptIds() {
        Long deptId = SecurityUtils.getDeptId();
        LoginUser loginUser = getLoginUser();
        if (loginUser.getUser().isAdmin()) {
            return R.ok(fixtureSharedInfoService.selectFixtureSharedInfoDeptIds(null));
        } else {
            return R.ok(fixtureSharedInfoService.selectFixtureSharedInfoDeptIds(deptId));
        }
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {

        ExcelUtil<FixtureSharedInfo> util = new ExcelUtil<>(FixtureSharedInfo.class);
        util.importTemplateExcel(response, "治具共享信息导入模板");
    }

    @PreAuthorize("@ss.hasPermi('fixture:search:upload')")
    @Log(title = "共治具导入", businessType = BusinessType.IMPORT)
    @PostMapping("/upload")
    public AjaxResult uploadFixtureInfo(MultipartFile file) throws Exception {
        ExcelUtil<FixtureSharedInfo> util = new ExcelUtil<>(FixtureSharedInfo.class);
        List<FixtureSharedInfo> fixtureSharedInfos = util.importExcel(file.getInputStream());
        Map<String, String> resultMap = fixtureSharedInfoService.uploadFixtureInfo(fixtureSharedInfos);

        return "1".equals(resultMap.get("flag")) ? AjaxResult.success(resultMap.get("result")) : AjaxResult.warn(resultMap.get("result"));
    }
}