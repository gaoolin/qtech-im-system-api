package com.qtech.im.eqn.domain;

import com.qtech.im.common.domain.ImEqsNetworkingAndRemoteInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/11/19 09:39:46
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceData extends ImEqsNetworkingAndRemoteInfo {
    private String receiveDate;
    private String deviceId;
    private String deviceType;
    private String remoteControl;

    private String status; // ONLINE / OFFLINE
    private LocalDateTime lastUpdated;
}
