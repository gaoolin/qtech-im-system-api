package com.qtech.im.wire.service;


import com.qtech.im.wire.domain.FactoryLevelWireUsage;

import java.util.List;

/**
 * 厂区级金线用量监控Service接口
 *
 * @author qtech
 * @date 2023-05-17
 */
public interface IFactoryLevelWireUsageService
{
    /**
     * 查询厂区级金线用量监控
     *
     * @param factoryName 厂区级金线用量监控主键
     * @return 厂区级金线用量监控
     */
    public FactoryLevelWireUsage selectFactoryLevelWireUsageByFactoryName(String factoryName);

    /**
     * 查询厂区级金线用量监控列表
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 厂区级金线用量监控集合
     */
    public List<FactoryLevelWireUsage> selectFactoryLevelWireUsageList(FactoryLevelWireUsage factoryLevelWireUsage);

    /**
     * 新增厂区级金线用量监控
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 结果
     */
    public int insertFactoryLevelWireUsage(FactoryLevelWireUsage factoryLevelWireUsage);

    /**
     * 修改厂区级金线用量监控
     *
     * @param factoryLevelWireUsage 厂区级金线用量监控
     * @return 结果
     */
    public int updateFactoryLevelWireUsage(FactoryLevelWireUsage factoryLevelWireUsage);

    /**
     * 批量删除厂区级金线用量监控
     *
     * @param factoryNames 需要删除的厂区级金线用量监控主键集合
     * @return 结果
     */
    public int deleteFactoryLevelWireUsageByFactoryNames(String[] factoryNames);

    /**
     * 删除厂区级金线用量监控信息
     *
     * @param factoryName 厂区级金线用量监控主键
     * @return 结果
     */
    public int deleteFactoryLevelWireUsageByFactoryName(String factoryName);
}
