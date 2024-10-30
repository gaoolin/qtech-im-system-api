package com.qtech.im.wire.domain;


import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 金线消耗量对象 data_clean_db.wb_wire_qty_usage_bak1
 *
 * @author gaozhilin
 * @date 2023-03-29
 */
public class EqLevelWireUsage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 厂区 */
    @Excel(name = "厂区")
    private String factoryName;

    /** 车间 */
    @Excel(name = "车间")
    private String workshop;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceMId;

    /** 机型 */
    @Excel(name = "机型")
    private String prodType;

    /** 线径 */
    @Excel(name = "线径")
    private String wireWidth;

    /** 实际用量 */
    @Excel(name = "实际用量")
    private Double actualWireUsage;

    /** 标准用量 */
    @Excel(name = "标准用量")
    private Double standardWireUsage;

    /** bom用量 */
    @Excel(name = "bom金线用量")
    private Double bomWireUsage;

    /** 产量 */
    @Excel(name = "产量")
    private Integer yield;

    /** 占比 */
    @Excel(name = "占比")
    private Double percents;

    /** 日期 */
    private Date createDate;

    /** 状态 */
    private Integer status;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getDeviceMId() {
        return deviceMId;
    }

    public void setDeviceMId(String deviceMId) {
        this.deviceMId = deviceMId;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getWireWidth() {
        return wireWidth;
    }

    public void setWireWidth(String wireWidth) {
        this.wireWidth = wireWidth;
    }

    public Double getActualWireUsage() {
        return actualWireUsage;
    }

    public void setActualWireUsage(Double actualWireUsage) {
        this.actualWireUsage = actualWireUsage;
    }

    public Double getStandardWireUsage() {
        return standardWireUsage;
    }

    public void setStandardWireUsage(Double standardWireUsage) {
        this.standardWireUsage = standardWireUsage;
    }

    public Double getBomWireUsage() {
        return bomWireUsage;
    }

    public void setBomWireUsage(Double bomWireUsage) {
        this.bomWireUsage = bomWireUsage;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Double getPercents() {
        return percents;
    }

    public void setPercents(Double percents) {
        this.percents = percents;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WireUsage{" +
                "factoryName='" + factoryName + '\'' +
                ", workshop='" + workshop + '\'' +
                ", deviceMId='" + deviceMId + '\'' +
                ", prodType='" + prodType + '\'' +
                ", wireWidth='" + wireWidth + '\'' +
                ", actualWireUsage=" + actualWireUsage +
                ", standardWireUsage=" + standardWireUsage +
                ", bomWireUsage=" + bomWireUsage +
                ", yield=" + yield +
                ", percents=" + percents +
                ", createDate=" + createDate +
                ", status=" + status +
                '}';
    }
}
