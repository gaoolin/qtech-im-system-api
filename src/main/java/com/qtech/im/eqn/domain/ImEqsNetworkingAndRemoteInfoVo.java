package com.qtech.im.eqn.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImEqsNetworkingAndRemoteInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

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
    @Excel(name = "联网状态", readConverterExp = "0=离线,1=在线")
    private Integer netStatus;
    @Excel(name = "远程状态", readConverterExp = "0=关闭,1=开启")
    private Integer remoteStatus;
    private Integer label;
}
