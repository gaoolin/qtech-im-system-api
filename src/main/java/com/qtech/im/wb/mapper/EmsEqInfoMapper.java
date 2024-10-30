package com.qtech.im.wb.mapper;

import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 16:44:19
 * desc   :
 */

@Repository
public interface EmsEqInfoMapper {

    public List<ImEqsNetworkingAndRemoteInfoVo> selectEmsEqInfoList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo);
}
