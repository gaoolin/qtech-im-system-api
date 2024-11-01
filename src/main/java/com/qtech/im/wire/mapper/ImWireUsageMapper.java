package com.qtech.im.wire.mapper;

import com.qtech.im.wire.domain.ImWireUsage;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:24:58
 * desc   :
 */


public interface ImWireUsageMapper {
    public List<ImWireUsage> selectEqLevelWireUsageList(ImWireUsage imWireUsage);

    public List<ImWireUsage> selectGroupLevelWireUsageList(ImWireUsage imWireUsage);

    public List<ImWireUsage> selectFactoryLevelWireUsageList(ImWireUsage imWireUsage);
}
