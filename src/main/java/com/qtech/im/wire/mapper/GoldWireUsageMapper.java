package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.ImGoldWireUsage;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:24:58
 * desc   :
 */


public interface GoldWireUsageMapper {
    public List<ImGoldWireUsage> selectEqLevelWireUsageList(ImGoldWireUsage imGoldWireUsage);

    public List<ImGoldWireUsage> selectGroupLevelWireUsageList(ImGoldWireUsage imGoldWireUsage);

    public List<ImGoldWireUsage> selectFactoryLevelWireUsageList(ImGoldWireUsage imGoldWireUsage);
}
