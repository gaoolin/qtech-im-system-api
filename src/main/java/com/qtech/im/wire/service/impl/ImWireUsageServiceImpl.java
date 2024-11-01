package com.qtech.im.wire.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wire.domain.ImWireUsage;
import com.qtech.im.wire.mapper.ImWireUsageMapper;
import com.qtech.im.wire.service.IImWireUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:23:08
 * desc   :
 */

@Slf4j
@DataSource(value = DataSourceType.SECOND)
@Service
public class ImWireUsageServiceImpl implements IImWireUsageService {

    @Autowired
    private ImWireUsageMapper imWireUsageMapper;
    @Override
    public List<ImWireUsage> selectEqLevelWireUsageList(ImWireUsage eqLevelWireUsage) {
        try {
            return imWireUsageMapper.selectEqLevelWireUsageList(eqLevelWireUsage);
        } catch (Exception e) {
            log.error("selectEqLevelWireUsageList:" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImWireUsage> selectGroupLevelWireUsageList(ImWireUsage eqLevelWireUsage) {
        try {
            return imWireUsageMapper.selectGroupLevelWireUsageList(eqLevelWireUsage);
        } catch (Exception e) {
            log.error("selectGroupLevelWireUsageList:" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImWireUsage> selectFactoryLevelWireUsageList(ImWireUsage eqLevelWireUsage) {
        try {
            return imWireUsageMapper.selectFactoryLevelWireUsageList(eqLevelWireUsage);
        } catch (Exception e) {
            log.error("selectFactoryLevelWireUsageList:" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }
}
