package com.qtech.im.fixture.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureSharedInfo;
import com.qtech.im.fixture.service.IFixtureSharedInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/06/28 08:20:42
 * desc   :  pogopin治具历史Controller
 */

@RestController
@RequestMapping("/fixture/history")
public class FixtrueSharedInfoHistoryController extends BaseController {

    @Autowired
    private IFixtureSharedInfoHistoryService fixtureSharedInfoHistoryService;

    @GetMapping("/list")
    public TableDataInfo list(FixtureSharedInfo fixtureSharedInfo) {
        startPage();
        List<FixtureSharedInfo> list = fixtureSharedInfoHistoryService.selectFixtureSharedInfoHistoryList(fixtureSharedInfo);
        return getDataTable(list);
    }
}
