package com.qtech.im.wire.service;

import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import com.qtech.im.wire.vo.ImGoldWireUsageVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:22:38
 * desc   :
 */


public interface IGoldWireUsageService {
    public QtechImVoUtil.QtechImVos<ImGoldWireUsageVo> selectEqLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);

    public List<ImGoldWireUsage> selectGroupLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);

    public List<ImGoldWireUsage> selectFactoryLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);
}
