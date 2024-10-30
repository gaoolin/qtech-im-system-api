package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureParamsAa;

import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/02 13:42:27
 * desc   :
 */


public interface IFixtureParamsAaService {
    List<FixtureParamsAa> selectFixtureParamsAaList(FixtureParamsAa fixtureParamsAa);

    int addFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    Map<String, String> uploadFixtureParamsAa(List<FixtureParamsAa> fixtureParamsAas);

    int editFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    int removeFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    FixtureParamsAa selectOneFixtureParamsAa(FixtureParamsAa fixtureParamsAa);
}
