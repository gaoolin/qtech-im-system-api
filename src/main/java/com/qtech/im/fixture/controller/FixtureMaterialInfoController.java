package com.qtech.im.fixture.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureMaterialInfo;
import com.qtech.im.fixture.service.IFixtureMaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/03 10:26:29
 * desc   :
 */

@RestController
@RequestMapping("/fixture/materialInfo")
public class FixtureMaterialInfoController extends BaseController {

    @Autowired
    IFixtureMaterialInfoService fixtureMaterialInfoService;

    @GetMapping("/exist")
    public R isMaterialExist(FixtureMaterialInfo fixtureMaterialInfo) {
        Boolean fixtureMaterialInfoExist = fixtureMaterialInfoService.isFixtureMaterialInfoExist(fixtureMaterialInfo);
        return fixtureMaterialInfoExist ? R.ok("error"): R.ok("ok");
    }

    @GetMapping("/{deptId}")
    public TableDataInfo getMaterialIds(@PathVariable Long deptId) {
        FixtureMaterialInfo fixtureMaterialInfo = new FixtureMaterialInfo();
        fixtureMaterialInfo.setDeptId(deptId);
        List<FixtureMaterialInfo> fixtureMaterialInfos = fixtureMaterialInfoService.selectFixtureMaterialInfoList(fixtureMaterialInfo);
        return getDataTable(fixtureMaterialInfos);
    }
}
