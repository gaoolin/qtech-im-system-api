package com.qtech.im.eqn.mapper;

import com.qtech.im.eqn.domain.ImEqsAndNetCntVo;
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

    // public List<ImEqsAndNetCntVo> selectEqNetworkingList(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsAndNetCntVo> selectEqNetworkingOfflineList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                                ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsAndNetCntVo> selectEqNetworkingAgg(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                        ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsAndNetCntVo> selectEqNetworkingList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                         ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo);
}
