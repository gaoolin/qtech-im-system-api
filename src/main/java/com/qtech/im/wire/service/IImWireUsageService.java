package com.qtech.im.wire.service;

import com.qtech.im.wire.domain.ImWireUsage;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:22:38
 * desc   :
 */


public interface IImWireUsageService {
    public List<ImWireUsage> selectEqLevelWireUsageList(ImWireUsage eqLevelWireUsage);

    public List<ImWireUsage> selectGroupLevelWireUsageList(ImWireUsage eqLevelWireUsage);

    public List<ImWireUsage> selectFactoryLevelWireUsageList(ImWireUsage eqLevelWireUsage);
}
