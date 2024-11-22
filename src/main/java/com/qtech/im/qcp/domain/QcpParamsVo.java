package com.qtech.im.qcp.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImEqsNetworkingAndRemoteInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/12/05 16:04:02
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QcpParamsVo extends ImEqsNetworkingAndRemoteInfo {
    @Excel(name = "qcp参数为空")
    private int qcpParamsIsNull;
    @Excel(name = "远程未开启")
    private int remoteStatusOff;
}
