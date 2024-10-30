package com.qtech.im.fixture.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.fixture.domain.FixtureStatisticsInfo;
import com.qtech.im.fixture.service.IFixtureStatisticsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/17 15:10:39
 * desc   :
 */

@RestController
@RequestMapping("/fixture/statistics")
public class FixtureStatisticsController extends BaseController {

    @Autowired
    IFixtureStatisticsInfoService fixtureStatisticsInfoService;

    @GetMapping("/panel")
    public R<List<FixtureStatisticsInfo>> getFixturePanelData() {
        List<FixtureStatisticsInfo> fixturePanelDatas = fixtureStatisticsInfoService.getFixturePanelData();

        return R.ok(fixturePanelDatas);
    }

    @GetMapping("/line")
    public TableDataInfo getFixtureTrendingData() {
        List<FixtureStatisticsInfo> fixtureTrendingData = fixtureStatisticsInfoService.getFixtureTrendingData();
        return getDataTable(fixtureTrendingData);
    }
}
