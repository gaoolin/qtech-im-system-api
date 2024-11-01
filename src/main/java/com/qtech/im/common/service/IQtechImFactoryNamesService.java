package com.qtech.im.common.service;

import com.qtech.im.common.domain.ImReportBaseInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 16:04:19
 * desc   :
 */


public interface IQtechImFactoryNamesService {
    public List<ImReportBaseInfo> getHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getLatestFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWireFactoryNames(ImReportBaseInfo imReportBaseInfo);
}
