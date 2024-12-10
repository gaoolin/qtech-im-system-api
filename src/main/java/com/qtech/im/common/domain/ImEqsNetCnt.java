package com.qtech.im.common.domain;

import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/09 15:03:11
 * desc   :  设备联网
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ImEqsNetCnt extends ImReportBaseInfo {
    private int ttlEqs;
    private int onlineEqs;
    private int offlineEqs;
}
