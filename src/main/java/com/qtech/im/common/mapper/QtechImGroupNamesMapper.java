package com.qtech.im.common.mapper;

import com.qtech.im.common.domain.ImReportBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 13:51:58
 * desc   :
 */

@Mapper
public interface QtechImGroupNamesMapper {
    public List<ImReportBaseInfo> getHistoryGroupNames(ImReportBaseInfo imReportBaseInfo);
    public List<ImReportBaseInfo> getLatestGroupNames(ImReportBaseInfo imReportBaseInfo);
    public List<ImReportBaseInfo> getWireGroupNames(ImReportBaseInfo imReportBaseInfo);
    public List<ImReportBaseInfo> getWbOlpGroupNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWbOlpLatestGroupNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getEqnGroupNames(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getQcpGroupNames(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getAaCtrlGroupNames(ImReportBaseInfo imReportBaseInfo);
}
