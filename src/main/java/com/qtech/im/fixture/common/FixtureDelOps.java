package com.qtech.im.fixture.common;

import com.qtech.common.utils.StringUtils;
import com.qtech.im.fixture.domain.FixtureParamsAa;
import com.qtech.im.fixture.domain.FixtureParamsLock;
import com.qtech.im.fixture.domain.FixtureParamsPoGoPin;
import com.qtech.im.fixture.mapper.FixtureParamsAaMapper;
import com.qtech.im.fixture.mapper.FixtureParamsLockMapper;
import com.qtech.im.fixture.mapper.FixtureParamsPoGoPinMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/04/19 08:40:31
 * desc   :
 */

@Slf4j
@Component
public class FixtureDelOps {

    @Autowired
    private FixtureParamsPoGoPinMapper fixtureParamsPoGoPinMapper;

    @Autowired
    private FixtureParamsAaMapper fixtureParamsAaMapper;

    @Autowired
    private FixtureParamsLockMapper fixtureParamsLockMapper;

    @Autowired
    private FixtureUtils fixtureUtils;

    public <T> int processOps(T fixtureParamsEntity, String project) {
        int result = 0;
        switch (project.toUpperCase()) {
            case "POGOPIN":
                if (fixtureParamsEntity instanceof FixtureParamsPoGoPin) {
                    FixtureParamsPoGoPin fixtureParamsPoGoPin = (FixtureParamsPoGoPin) fixtureParamsEntity;
                    result = fixtureParamsPoGoPinMapper.removeFixtureParamsPoGoPin(fixtureParamsPoGoPin);
                    fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsPoGoPin, project);
                } else {
                    throw new IllegalArgumentException("数据缺少料号信息，请检查！");
                }
                break;
            case "AA":
                if (fixtureParamsEntity instanceof FixtureParamsAa) {
                    FixtureParamsAa fixtureParamsAa = (FixtureParamsAa) fixtureParamsEntity;
                    result = fixtureParamsAaMapper.removeFixtureParamsAa(fixtureParamsAa);
                    fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsAa, project);
                } else {
                    throw new IllegalArgumentException("数据缺少料号信息，请检查！");
                }
                break;
            case "LOCK":
                if (fixtureParamsEntity instanceof FixtureParamsLock) {
                    FixtureParamsLock fixtureParamsLock = (FixtureParamsLock) fixtureParamsEntity;
                    result = fixtureParamsLockMapper.removeFixtureParamsLock(fixtureParamsLock);
                    fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsLock, project);
                } else {
                    throw new IllegalArgumentException("数据缺少料号信息，请检查！");
                }
            default:
                break;
        }
        return result;
    }

    public void validateParameters(Object fixtureParamsEntity, String project) {
        if (fixtureParamsEntity == null || StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("数据缺少料号信息，请检查！");
        }
    }

}
