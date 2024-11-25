package com.qtech.im.common.service;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImReportBaseInfo;
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

    Boolean iotQcpStatus(ImReportBaseInfo imReportBaseInfo);

    public Boolean iotAaStatus(ImReportBaseInfo imReportBaseInfo);
}
