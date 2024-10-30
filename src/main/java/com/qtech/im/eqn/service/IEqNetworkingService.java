package com.qtech.im.eqn.service;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:44:05
 * desc   :
 */


public interface IEqNetworkingService {

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> getFactoryNames();

    public List<ImEqsNetworkingAndRemoteInfoVo> getWorkshopNames(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingOfflineList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingAgg(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}
