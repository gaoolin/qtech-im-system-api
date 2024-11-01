package com.qtech.im.wire.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 金线标准用量信息对象 data_clean_db.ads_standard_wirebond_usage
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImWireUsageStandard extends ImWireUsage {

    /**
     * 标签
     */
    private String flag;
}
