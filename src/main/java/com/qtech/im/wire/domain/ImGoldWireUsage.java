package com.qtech.im.wire.domain;

import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:13:31
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImGoldWireUsage extends ImReportBaseInfo {
    private String wireWidth;
    private Double actualWireUsage;
    private Double standardWireUsage;
    private Double bomWireUsage;
    private Integer yield;
    private Double percents;
}
