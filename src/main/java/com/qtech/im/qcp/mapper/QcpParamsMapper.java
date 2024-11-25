package com.qtech.im.qcp.mapper;

import com.qtech.im.qcp.domain.QcpParamsDetailVo;
import com.qtech.im.qcp.domain.QcpParamsVo;
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

    public List<QcpParamsVo> selectQcpParamsOverviewList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, QcpParamsVo qcpParams);

    public List<QcpParamsDetailVo> selectQcpParamsList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes, QcpParamsDetailVo qcpParamsDetailVo);

    public String getMaxTime();

    public boolean checkIotStatus(@Param("deviceTypes") List<String> deviceTypes);
}
