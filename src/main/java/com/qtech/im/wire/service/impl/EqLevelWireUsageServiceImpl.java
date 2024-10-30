package com.qtech.im.wire.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wire.domain.EqLevelWireUsage;
import com.qtech.im.wire.mapper.EqLevelWireUsageMapper;
import com.qtech.im.wire.service.IEqLevelWireUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 金线消耗量Service业务层处理
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Slf4j
@DataSource(value = DataSourceType.SECOND)
@Service
public class EqLevelWireUsageServiceImpl implements IEqLevelWireUsageService
{
    @Autowired
    private EqLevelWireUsageMapper eqLevelWireUsageMapper;

    /**
     * 查询金线消耗量列表
     *
     * @param eqLevelWireUsage 金线消耗量
     * @return 金线消耗量
     */
    @Override
    public List<EqLevelWireUsage> selectEqLevelWireUsageList(EqLevelWireUsage eqLevelWireUsage)
    {
        try {
            return eqLevelWireUsageMapper.selectEqLevelWireUsageList(eqLevelWireUsage);
        } catch (Exception e) {
            log.error("selectEqLevelWireUsageList error", e);
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人！");
        }
    }
}
