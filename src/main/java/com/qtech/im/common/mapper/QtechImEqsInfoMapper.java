package com.qtech.im.common.mapper;

import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.eqn.domain.ImEqsAndNetCntVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 14:06:31
 * desc   :
 */

@Mapper
public interface QtechImEqsInfoMapper {
    public List<ImEqsAndNetCntVo> listEqsInfo(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public Boolean iotQcpStatus(@Param("deviceTypes") List<String> deviceTypes, ImReportBaseInfo imReportBaseInfo);

    public Boolean iotAaStatus(ImReportBaseInfo imReportBaseInfo);

    public Boolean iotStatus(ImReportBaseInfo imReportBaseInfo);
}
