package com.qtech.im.wire.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/09 17:17:25
 * desc   :  金线用量
 */

@Data
public class ImGoldWireEqUsageVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "设备编号")
    private String eqId;
    @Excel(name = "机种")
    private String prodType;

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

    public ImGoldWireEqUsageVo(ImGoldWireUsage imGoldWireUsage) {
        this.factoryName = imGoldWireUsage.getFactoryName();
        this.groupName = imGoldWireUsage.getGroupName();
        this.eqId = imGoldWireUsage.getEqId();
        this.mcId = imGoldWireUsage.getMcId();
        this.prodType = imGoldWireUsage.getProdType();
        this.wireWidth = imGoldWireUsage.getWireWidth();
        this.actualWireUsage = imGoldWireUsage.getActualWireUsage();
        this.standardWireUsage = imGoldWireUsage.getStandardWireUsage();
        this.bomWireUsage = imGoldWireUsage.getBomWireUsage();
        this.yield = imGoldWireUsage.getYield();
        this.percents = imGoldWireUsage.getPercents();
    }
}
