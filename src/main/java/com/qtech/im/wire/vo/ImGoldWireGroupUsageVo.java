package com.qtech.im.wire.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/23 10:31:09
 * desc   :
 */

@Data
public class ImGoldWireGroupUsageVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "机型")
    private String propType;
    @Excel(name = "线径")
    private String wireWidth;
    @Excel(name = "实际用量")
    private Double actualWireUsage;
    @Excel(name = "标准用量")
    private Double standardWireUsage;
    @Excel(name = "bom金线用量")
    private Double bomWireUsage;
    @Excel(name = "产量")
    private Integer yield;
    @Excel(name = "占比")
    private Double percents;

    public ImGoldWireGroupUsageVo(ImGoldWireUsage imGoldWireUsage) {
        this.factoryName = imGoldWireUsage.getFactoryName();
        this.groupName = imGoldWireUsage.getGroupName();
        this.propType = imGoldWireUsage.getProdType();
        this.wireWidth = imGoldWireUsage.getWireWidth();
        this.actualWireUsage = imGoldWireUsage.getActualWireUsage();
        this.standardWireUsage = imGoldWireUsage.getStandardWireUsage();
        this.bomWireUsage = imGoldWireUsage.getBomWireUsage();
        this.yield = imGoldWireUsage.getYield();
        this.percents = imGoldWireUsage.getPercents();
    }
}
