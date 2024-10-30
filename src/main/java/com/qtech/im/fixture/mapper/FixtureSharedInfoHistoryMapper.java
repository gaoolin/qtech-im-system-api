package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureSharedInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/06/28 08:22:57
 * desc   :
 */

@Repository
public interface FixtureSharedInfoHistoryMapper {
    List<FixtureSharedInfo> selectFixtureSharedInfoHistoryList(FixtureSharedInfo fixtureSharedInfo);

    int addFixtureSharedInfoHistory(FixtureSharedInfo fixtureSharedInfo);
}
