package com.qtech.im.common.mapper;

import com.qtech.im.common.domain.ImReportBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 16:03:42
 * desc   :
 */

@Mapper
public interface QtechImFactoryNamesMapper {
    public List<ImReportBaseInfo> getHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getLatestFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWireFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWbOlpFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getWbOlpLatestFactoryNames(ImReportBaseInfo imReportBaseInfo);

    public List<ImReportBaseInfo> getEqnFactoryNames(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes);

    public List<ImReportBaseInfo> getQcpFactoryNames(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes);

    public List<ImReportBaseInfo> getAaCtrlFactoryNames(ImReportBaseInfo imReportBaseInfo);
}
