package com.qtech.im.wb.service;

import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 17:00:34
 * desc   :
 */


public interface IEmsEqInfoService {

    public List<ImEqsNetAndRemoteInfo> selectEmsEqInfoList(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo);
}
