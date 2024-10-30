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
 * date   :  2024/04/17 14:02:54
 * desc   :
 */

@Slf4j
@Component
public class FixtureEditOps {

    @Autowired
    private FixtureUtils fixtureUtils;

    @Autowired
    private FixtureParamsPoGoPinMapper fixtureParamsPoGoPinMapper;

    @Autowired
    private FixtureParamsAaMapper fixtureParamsAaMapper;

    @Autowired
    private FixtureParamsLockMapper fixtureParamsLockMapper;

    public <T> int fixtureParamsEditOps(T fixtureParamsEntity, String project) {
        // 参数校验
        validateParameters(fixtureParamsEntity, project);
        int result = 0;
        fixtureUtils.fixtureCategoryExistCheckGeneric(fixtureParamsEntity, project);

        switch (project.toUpperCase()) {
            case "POGOPIN":
                result = processPoGoPin(fixtureParamsEntity, project);
                break;
            case "AA":
                result = processAa(fixtureParamsEntity, project);
                break;
            case "LOCK":
                result = processLock(fixtureParamsEntity, project);
                break;
            default:
                break;
        }
        return result;
    }

    private <T> int processPoGoPin(T fixtureEntity, String project) {
        int result = 0;
        if (fixtureEntity instanceof FixtureParamsPoGoPin) {
            FixtureParamsPoGoPin fixtureParamsPoGoPin = (FixtureParamsPoGoPin) fixtureEntity;
            fixtureParamsPoGoPinMapper.editFixtureParamsPoGoPin(fixtureParamsPoGoPin);
            fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsPoGoPin, project);
        }
        return result;
    }

    private <T> int processAa(T fixtureEntity, String project) {
        int result = 0;
        if (fixtureEntity instanceof FixtureParamsAa) {
            FixtureParamsAa fixtureParamsAa = (FixtureParamsAa) fixtureEntity;
            fixtureParamsAaMapper.editFixtureParamsAa(fixtureParamsAa);
            fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsAa, project);
        }
        return result;
    }

    private <T> int processLock(T fixtureEntity, String project) {
        int result = 0;
        if (fixtureEntity instanceof FixtureParamsLock) {
            FixtureParamsLock fixtureParamsLock = (FixtureParamsLock) fixtureEntity;
            result = fixtureParamsLockMapper.editFixtureParamsLock(fixtureParamsLock);
            fixtureUtils.fixtureMaterialCheckGeneric(fixtureParamsLock, project);
        }
        return result;
    }

    public void validateParameters(Object fixtureParamsEntity, String project) {
        if (fixtureParamsEntity == null || StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("数据缺少料号信息，请检查！");
        }
    }
}
