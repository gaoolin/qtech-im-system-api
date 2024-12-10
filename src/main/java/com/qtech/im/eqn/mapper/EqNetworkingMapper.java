package com.qtech.im.eqn.mapper;

import com.qtech.im.common.domain.ImEqsNetCnt;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
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
    public List<ImEqsNetAndRemoteInfo> selectEqNetworkingOfflineList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                                     ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo);

    public List<ImEqsNetCnt> selectEqNetworkingAgg(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                             ImEqsNetCnt imEqsNetAndRemoteInfo);

    public List<ImEqsNetAndRemoteInfo> selectEqNetworkingList(@Param("deptNames") List<String> deptNames, @Param("deviceTypes") List<String> deviceTypes,
                                                              ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo);
}
