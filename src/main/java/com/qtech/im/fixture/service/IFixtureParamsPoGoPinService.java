package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureParamsPoGoPin;

import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/03/07 15:40:23
 * desc   :
 */


public interface IFixtureParamsPoGoPinService {

    public List<FixtureParamsPoGoPin> selectFixtureParamsPoGoPinList(FixtureParamsPoGoPin FixtureParamsPoGoPin);

    public FixtureParamsPoGoPin selectOneFixtureParamsPoGoPin(Long id);

    public int addFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin);

    public int editFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin);

    public Map<String, String> uploadFixtureParamsPoGoPin(List<FixtureParamsPoGoPin> FixtureParamsPoGoPins);

    public FixtureParamsPoGoPin selectOneFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin);

    public int removeFixtureParamsPoGoPin(FixtureParamsPoGoPin FixtureParamsPoGoPin);
}
