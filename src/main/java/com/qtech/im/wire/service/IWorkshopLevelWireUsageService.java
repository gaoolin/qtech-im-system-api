package com.qtech.im.wire.service;

import com.qtech.im.wire.domain.WorkshopLevelWireUsage;

import java.util.List;

/**
 * 车间级金线用量监控Service接口
 *
 * @author qtech
 * @date 2023-05-16
 */
public interface IWorkshopLevelWireUsageService
{
    /**
     * 查询车间级金线用量监控
     *
     * @param factoryName 车间级金线用量监控主键
     * @return 车间级金线用量监控
     */
    public WorkshopLevelWireUsage selectWorkshopLevelWireUsageByFactoryName(String factoryName);

    /**
     * 查询车间级金线用量监控列表
     *
     * @param workshopLevelWireUsage 车间级金线用量监控
     * @return 车间级金线用量监控集合
     */
    public List<WorkshopLevelWireUsage> selectWorkshopLevelWireUsageList(WorkshopLevelWireUsage workshopLevelWireUsage);

    /**
     * 新增车间级金线用量监控
     *
     * @param workshopLevelWireUsage 车间级金线用量监控
     * @return 结果
     */
    public int insertWorkshopLevelWireUsage(WorkshopLevelWireUsage workshopLevelWireUsage);

    /**
     * 修改车间级金线用量监控
     *
     * @param workshopLevelWireUsage 车间级金线用量监控
     * @return 结果
     */
    public int updateWorkshopLevelWireUsage(WorkshopLevelWireUsage workshopLevelWireUsage);

    /**
     * 批量删除车间级金线用量监控
     *
     * @param factoryNames 需要删除的车间级金线用量监控主键集合
     * @return 结果
     */
    public int deleteWorkshopLevelWireUsageByFactoryNames(String[] factoryNames);

    /**
     * 删除车间级金线用量监控信息
     *
     * @param factoryName 车间级金线用量监控主键
     * @return 结果
     */
    public int deleteWorkshopLevelWireUsageByFactoryName(String factoryName);
}
