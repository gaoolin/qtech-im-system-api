package com.qtech.im.wire.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
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
public class ImWireUsage extends ImReportBaseInfo {
    /**
     * 线径
     */
    @Excel(name = "线径")
    private String wireWidth;

    /**
     * 实际用量
     */
    @Excel(name = "实际用量")
    private Double actualWireUsage;

    /**
     * 标准用量
     */
    @Excel(name = "标准用量")
    private Double standardWireUsage;

    /**
     * bom用量
     */
    @Excel(name = "bom金线用量")
    private Double bomWireUsage;

    /**
     * 产量
     */
    @Excel(name = "产量")
    private Integer yield;

    /**
     * 占比
     */
    @Excel(name = "占比")
    private Double percents;
}
