package com.qtech.im.common.service;

import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.eqn.domain.ImEqsAndNetCntVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 14:10:14
 * desc   :
 */


public interface IQtechImEqsInfoService {
    public List<ImEqsAndNetCntVo> listEqsInfo(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public Boolean iotQcpStatus(ImReportBaseInfo imReportBaseInfo);

    public Boolean iotAaStatus(ImReportBaseInfo imReportBaseInfo);

    public Boolean iotStatus(ImReportBaseInfo imReportBaseInfo);
}
