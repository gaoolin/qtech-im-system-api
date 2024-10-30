package com.qtech.im.fixture.controller;

import com.github.pagehelper.PageHelper;
import com.qtech.common.utils.ServletUtils;
import com.qtech.common.utils.sql.SqlUtil;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureCategoryInfo;
import com.qtech.im.fixture.service.IFixtureCategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/25 20:11:08
 * desc   :  治具类型控制器
 */

@RestController
@RequestMapping("/fixture/category")
public class FixtureCategoryInfoController extends BaseController {

    @Autowired
    private IFixtureCategoryInfoService fixtureCategoryInfoService;

    /**
     * 获取治具类型
     */
    @GetMapping("/list")
    public TableDataInfo getCategoryList(FixtureCategoryInfo fixtureCategoryInfo) {
        // 治具类型管理模态框，自定义分页参数
        int fixtureCategoryPageNum = Integer.parseInt(ServletUtils.getParameter("fixtureCategoryPageNum"));
        int fixtureCategoryPageSize = Integer.parseInt(ServletUtils.getParameter("fixtureCategoryPageSize"));
        String orderBy = SqlUtil.escapeOrderBySql("");
        PageHelper.startPage(fixtureCategoryPageNum, fixtureCategoryPageSize, orderBy).setReasonable(true);
        List<FixtureCategoryInfo> fixtureCategoryList = fixtureCategoryInfoService.selectFixtureCategoryList(fixtureCategoryInfo);
        //return R.ok(fixtureCategoryList);
        return getDataTable(fixtureCategoryList);
    }

    @GetMapping("/all")
    public R getCategoryAll(FixtureCategoryInfo fixtureCategoryInfo) {
        List<FixtureCategoryInfo> all = fixtureCategoryInfoService.selectFixtureCategoryAll(fixtureCategoryInfo);
        return R.ok(all);
    }

    @PostMapping
    public AjaxResult add(@RequestBody FixtureCategoryInfo fixtureCategoryInfo) {
        return toAjax(fixtureCategoryInfoService.addFixtureCategoryInfo(fixtureCategoryInfo));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody FixtureCategoryInfo fixtureCategoryInfo) {
        return toAjax(fixtureCategoryInfoService.editFixtureCategoryInfo(fixtureCategoryInfo));
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long cId) {
        return toAjax(fixtureCategoryInfoService.removeFixtureCategoryInfo(cId));
    }
}
