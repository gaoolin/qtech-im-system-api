package com.qtech.im.eqn.mapper;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
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

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> getFactoryNames();

    public List<ImEqsNetworkingAndRemoteInfoVo> getWorkshopNames(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingOfflineList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingAgg(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}
