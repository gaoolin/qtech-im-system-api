package com.qtech.im.common.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/25 08:37:11
 * desc   :  设备联网和远程信息
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImEqsNetworkingAndRemoteInfo extends ImReportBaseInfo {
    @Excel(name = "总设备数")
    private int ttlEqs;
    @Excel(name = "联网设备数")
    private int onlineEqs;
    @Excel(name = "离线设备数")
    private int offlineEqs;
    /** int 默认值 0 */
    @Excel(name = "远程状态码")
    private int remoteCode;
}
