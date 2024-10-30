package com.qtech.im.common.service;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 14:10:14
 * desc   :
 */


public interface IQtechImEqsInfoService {
    public List<ImEqsNetworkingAndRemoteInfoVo> listEqsInfo(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}
