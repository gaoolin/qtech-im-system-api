package com.qtech.im.eqn.service;

import com.qtech.im.eqn.domain.ImEqsAndNetCntVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:44:05
 * desc   :
 */


public interface IEqNetworkingService {

    public List<ImEqsAndNetCntVo> selectEqNetworkingList(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsAndNetCntVo> selectEqNetworkingOfflineList(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsAndNetCntVo> selectEqNetworkingAgg(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);
}
