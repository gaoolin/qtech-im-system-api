package com.qtech.im.qcp.mapper;

import com.qtech.im.qcp.domain.QcpParamsDetail;
import com.qtech.im.qcp.domain.QcpParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:50:51
 * desc   :
 */

@Repository
public interface QcpParamsMapper {

    public List<QcpParams> selectQcpParamsOverviewList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, QcpParams qcpParams);

    public List<QcpParamsDetail> selectQcpParamsList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, QcpParamsDetail qcpParamsDetail);

    public String getMaxTime();

    public boolean checkIotStatus(@Param("deviceTypes") List<String> deviceTypes);
}
