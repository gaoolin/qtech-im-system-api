package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureParamsLock;

import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/16 14:31:25
 * desc   :
 */


public interface IFixtureParamsLockService {
    List<FixtureParamsLock> selectFixtureParamsLockList(FixtureParamsLock fixtureParamsLock);

    int addFixtureParamsLock(FixtureParamsLock fixtureParamsLock);

    int editFixtureParamsLock(FixtureParamsLock fixtureParamsLock);

    FixtureParamsLock selectOneFixtureParamsLock(FixtureParamsLock fixtureParamsLock);

    int removeFixtureParamsLock(FixtureParamsLock fixtureParamsLock);

    Map<String, String> uploadFixtureParamsLock(List<FixtureParamsLock> fixtureParamsLocks);
}
