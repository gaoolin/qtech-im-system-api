package com.qtech.im.wb.service;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 17:00:34
 * desc   :
 */


public interface IEmsEqInfoService {

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEmsEqInfoList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}