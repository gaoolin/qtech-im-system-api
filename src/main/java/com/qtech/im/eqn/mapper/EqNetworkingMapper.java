package com.qtech.im.eqn.mapper;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:45:32
 * desc   :
 */

@Repository
public interface EqNetworkingMapper {

    // public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingOfflineList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                                              ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingAgg(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                                      ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                                       ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}
