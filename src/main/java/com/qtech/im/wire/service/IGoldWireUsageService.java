package com.qtech.im.wire.service;

import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import com.qtech.im.wire.vo.ImGoldWireEqUsageVo;
import com.qtech.im.wire.vo.ImGoldWireFactoryUsageVo;
import com.qtech.im.wire.vo.ImGoldWireGroupUsageVo;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:22:38
 * desc   :
 */


public interface IGoldWireUsageService {
    public QtechImVoUtil.QtechImVos<ImGoldWireEqUsageVo> selectEqLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);

    public QtechImVoUtil.QtechImVos<ImGoldWireGroupUsageVo> selectGroupLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);

    public QtechImVoUtil.QtechImVos<ImGoldWireFactoryUsageVo> selectFactoryLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage);
}
