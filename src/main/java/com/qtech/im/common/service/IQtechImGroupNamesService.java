package com.qtech.im.common.service;

import com.qtech.im.common.domain.ImReportBaseInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 13:49:41
 * desc   :
 */


public interface IQtechImGroupNamesService {

    public List<ImReportBaseInfo> getHistoryGroupNames(ImReportBaseInfo imReportBaseInfo);
    public List<ImReportBaseInfo> getLatestGroupNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWireGroupNames(ImReportBaseInfo imReportBaseInfo);
}
