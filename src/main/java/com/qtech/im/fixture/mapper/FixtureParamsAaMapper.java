package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureParamsAa;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/02 13:46:36
 * desc   :
 */

@Repository
public interface FixtureParamsAaMapper {

    List<FixtureParamsAa> selectFixtureParamsAaList(FixtureParamsAa fixtureParamsAa);

    int addFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    int editFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    int removeFixtureParamsAa(FixtureParamsAa fixtureParamsAa);

    FixtureParamsAa selectOneFixtureParamsAa(FixtureParamsAa fixtureParamsAa);
}
