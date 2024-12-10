package com.qtech.im.eqn.service;

import com.qtech.im.common.domain.ImEqsNetCnt;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
import com.qtech.im.eqn.vo.ImEqsNetAndRemoteInfoVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:44:05
 * desc   :
 */


public interface IEqNetworkingService {

    public QtechImVoUtil.QtechImVos<ImEqsNetAndRemoteInfoVo> list(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo);

    public List<ImEqsNetAndRemoteInfo> offlineList(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo);

    public List<ImEqsNetCnt> agg(ImEqsNetCnt imEqsNetAndRemoteInfo);
}
