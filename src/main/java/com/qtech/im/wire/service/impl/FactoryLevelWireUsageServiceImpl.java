package com.qtech.im.wire.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wire.domain.FactoryLevelWireUsage;
import com.qtech.im.wire.mapper.FactoryLevelWireUsageMapper;
import com.qtech.im.wire.service.IFactoryLevelWireUsageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厂区级金线用量监控Service业务层处理
 *
 * @author qtech
 * @date 2023-05-17
 */
@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class FactoryLevelWireUsageServiceImpl implements IFactoryLevelWireUsageService
{
    @Autowired
    private FactoryLevelWireUsageMapper factoryLevelWireUsageMapper;

    /**
     * 查询厂区级金线用量监控
     *
     * @param factoryName 厂区级金线用量监控主键
     * @return 厂区级金线用量监控
     */
    @Override
    public FactoryLevelWireUsage selectFactoryLevelWireUsageByFactoryName(String factoryName)
    {
        try {
            return factoryLevelWireUsageMapper.selectFactoryLevelWireUsageByFactoryName(factoryName);
        } catch (Exception e) {
            log.error("selectFactoryLevelWireUsageByFactoryName error", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 查询厂区级金线用量监控列表
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 厂区级金线用量监控
     */
    @Override
    public List<FactoryLevelWireUsage> selectFactoryLevelWireUsageList(FactoryLevelWireUsage factoryLevelWireUsage)
    {
        try {
            return factoryLevelWireUsageMapper.selectFactoryLevelWireUsageList(factoryLevelWireUsage);
        } catch (Exception e) {
            log.error("selectFactoryLevelWire error", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 新增厂区级金线用量监控
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 结果
     */
    @Override
    public int insertFactoryLevelWireUsage(FactoryLevelWireUsage factoryLevelWireUsage)
    {
        try {
            return factoryLevelWireUsageMapper.insertFactoryLevelWireUsage(factoryLevelWireUsage);
        } catch (Exception e) {
            log.error("insertFactoryLevelWireUsage error", e);
            throw new RuntimeException("数据库处理发生异常，请联系统负责人！");
        }
    }

    /**
     * 修改厂区级金线用量监控
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 结果
     */
    @Override
    public int updateFactoryLevelWireUsage(FactoryLevelWireUsage factoryLevelWireUsage)
    {
        try {
            return factoryLevelWireUsageMapper.updateFactoryLevelWireUsage(factoryLevelWireUsage);
        } catch (Exception e) {
            log.error("updateFactoryLevelWireUsage error", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 批量删除厂区级金线用量监控
     *
     * @param factoryNames 需要删除的厂区级金线用量监控主键
     * @return 结果
     */
    @Override
    public int deleteFactoryLevelWireUsageByFactoryNames(String[] factoryNames)
    {
        try {
            return factoryLevelWireUsageMapper.deleteFactoryLevelWireUsageByFactoryNames(factoryNames);
        } catch (Exception e) {
            log.error("deleteFactoryLevelWireUsageByFactoryNames error", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }

    /**
     * 删除厂区级金线用量监控信息
     *
     * @param factoryName 厂区级金线用量监控主键
     * @return 结果
     */
    @Override
    public int deleteFactoryLevelWireUsageByFactoryName(String factoryName)
    {
        try {
            return factoryLevelWireUsageMapper.deleteFactoryLevelWireUsageByFactoryName(factoryName);
        } catch (Exception e) {
            log.error("deleteFactoryLevelWireUsageByFactoryName error", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }
}
