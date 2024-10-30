package com.qtech.im.eqn.domain;

import com.qtech.im.common.domain.ImEqsNetworkingAndRemoteInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/25 09:54:55
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImEqsNetworkingAndRemoteInfoVo extends ImEqsNetworkingAndRemoteInfo {
    private Integer status;
    private Integer label;
}
