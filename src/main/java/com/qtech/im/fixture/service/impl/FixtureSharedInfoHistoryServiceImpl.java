package com.qtech.im.fixture.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.fixture.domain.FixtureSharedInfo;
import com.qtech.im.fixture.mapper.FixtureSharedInfoHistoryMapper;
import com.qtech.im.fixture.service.IFixtureSharedInfoHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/06/28 09:20:42
 * desc   :
 */

@Slf4j
@Service
public class FixtureSharedInfoHistoryServiceImpl implements IFixtureSharedInfoHistoryService {

    @Autowired
    FixtureSharedInfoHistoryMapper fixtureSharedInfoHistoryMapper;

    @DataScope(deptAlias = "ta")
    @Override
    public List<FixtureSharedInfo> selectFixtureSharedInfoHistoryList(FixtureSharedInfo fixtureSharedInfo) {
        try {
            return fixtureSharedInfoHistoryMapper.selectFixtureSharedInfoHistoryList(fixtureSharedInfo);
        } catch (Exception e) {
            log.error("selectFixtureSharedInfoHistoryList error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer addFixtureSharedInfoHistory(FixtureSharedInfo fixtureSharedInfo) {
        try {
            return fixtureSharedInfoHistoryMapper.addFixtureSharedInfoHistory(fixtureSharedInfo);
        } catch (Exception e) {
            log.error("addFixtureSharedInfoHistory error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
