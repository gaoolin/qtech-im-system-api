package com.qtech.im.fixture.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.qtech.common.utils.http.HttpUtils;
import com.qtech.im.fixture.domain.FixtureStatisticsInfo;
import com.qtech.im.fixture.mapper.FixtureStatisticsInfoMapper;
import com.qtech.im.fixture.service.IFixtureStatisticsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/17 15:32:49
 * desc   :
 */

@Slf4j
@Service
public class FixtureStatisticsInfoServiceImpl implements IFixtureStatisticsInfoService {

    @Autowired
    FixtureStatisticsInfoMapper fixtureStatisticsInfoMapper;

    @Value("${fixture.statistics.url}")
    private String statisticsUrl;

    @Override
    public List<FixtureStatisticsInfo> getFixturePanelData() {
        try {
            return fixtureStatisticsInfoMapper.selectFixturePanelData();
        } catch (Exception e) {
            log.error("getFixturePanelData error" , e);
            throw new RuntimeException("查询出错，请联系系统负责人！");
        }
    }

    @Override
    public List<FixtureStatisticsInfo> getFixtureTrendingData() {
        // List<FixtureStatisticsInfo> fixtureHistoryTrendingData = fixtureStatisticsInfoMapper.getFixtureHistoryTrendingData();

        // fixtureHistoryTrendingData.sort(Comparator.comparing(FixtureStatisticsInfo::getWeekNum));

        // String s = HttpUtils.sendGet("http://10.170.6.40:31735/fixture/statistics/line");
        String s = HttpUtils.sendGet(statisticsUrl);

        if ("[]".equals(s)) {
            // return fixtureHistoryTrendingData;
            return new ArrayList<>();
        }

        List<FixtureStatisticsInfo> ratioAll = JSON.parseObject(s, new TypeReference<ArrayList<FixtureStatisticsInfo>>() {
        });
        log.info(">>>>> Get Python Service Data fixtureTrendingData:{}" , ratioAll);
        // String weekNum = ratioAll.get(ratioAll.size() - 1).getWeekNum();

        // fixtureHistoryTrendingData.addAll(ratioAll.stream().filter(x -> weekNum.equals(x.getWeekNum())).collect(Collectors.toList()));

        ratioAll.sort(Comparator.comparing(FixtureStatisticsInfo::getWeekNum));

        return ratioAll;
    }
}
