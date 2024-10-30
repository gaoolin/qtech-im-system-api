package com.qtech.im.wire.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.im.wire.domain.EqLevelWireUsage;
import com.qtech.im.wire.domain.FactoryNameList;
import com.qtech.im.wire.domain.WorkshopNameList;
import com.qtech.im.wire.service.IFactoryNameService;
import com.qtech.im.wire.service.IWorkshopNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/27 14:11:53
 * @description :
 */

@RestController
@RequestMapping("/biz")
public class FactoryNameListController extends BaseController {

    @Autowired
    private IFactoryNameService factoryNameService;

    @Autowired
    private IWorkshopNameService workshopNameService;

    @GetMapping("/factoryName")
    public R<List<FactoryNameList>> factoryList() {
        List<FactoryNameList> list = factoryNameService.SelectFactoryNameList();
        return R.ok(list);
    }

    @GetMapping("/workshopName")
    public R<List<WorkshopNameList>> workshopList(EqLevelWireUsage eqLevelWireUsage) {
        List<WorkshopNameList> list = workshopNameService.selectWorkshopNameList(eqLevelWireUsage);
        return R.ok(list);
    }
}
