package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureStatisticsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/17 15:24:22
 * desc   :
 */


@Repository
public interface FixtureStatisticsInfoMapper {

    public List<FixtureStatisticsInfo> selectFixturePanelData();

    public List<FixtureStatisticsInfo> getFixtureTrendingData(@Param(value = "dt") String dt);

    public FixtureStatisticsInfo getFixtureTrendingDataCurrentWeek();

    public List<FixtureStatisticsInfo> getFixtureHistoryTrendingData();
}
