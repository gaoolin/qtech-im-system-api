package com.qtech.im.wire.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 车间级金线用量监控对象 workshop_level_wire_usage
 *
 * @author qtech
 * @date 2023-05-16
 */
public class WorkshopLevelWireUsage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 厂区 */
    @Excel(name = "厂区")
    private String factoryName;

    /** 车间 */
    @Excel(name = "车间")
    private String workshop;

    /** 机型 */
    @Excel(name = "机型")
    private String prodType;

    /** 线径 */
    @Excel(name = "线径")
    private String wireWidth;

    /** 实际用量 */
    @Excel(name = "实际用量")
    private Float actualWireUsage;

    /** 维护用量 */
    @Excel(name = "维护用量")
    private Float standardWireUsage;

    /** BOM用量 */
    @Excel(name = "BOM用量")
    private Float bomWireUsage;

    /** 产量 */
    @Excel(name = "产量")
    private Long yield;

    /** 差异(%) */
    @Excel(name = "差异(%)")
    private Float percents;

    /** 日期 */
    private Date createDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    public Float getActualWireUsage() {
        return actualWireUsage;
    }

    public void setActualWireUsage(Float actualWireUsage) {
        this.actualWireUsage = actualWireUsage;
    }

    public Float getStandardWireUsage() {
        return standardWireUsage;
    }

    public void setStandardWireUsage(Float standardWireUsage) {
        this.standardWireUsage = standardWireUsage;
    }

    public Float getBomWireUsage() {
        return bomWireUsage;
    }

    public void setBomWireUsage(Float bomWireUsage) {
        this.bomWireUsage = bomWireUsage;
    }

    public Long getYield() {
        return yield;
    }

    public void setYield(Long yield) {
        this.yield = yield;
    }

    public Float getPercents() {
        return percents;
    }

    public void setPercents(Float percents) {
        this.percents = percents;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkshopLevelWireUsage{" +
                "factoryName='" + factoryName + '\'' +
                ", workshop='" + workshop + '\'' +
                ", prodType='" + prodType + '\'' +
                ", wireWidth='" + wireWidth + '\'' +
                ", actualWireUsage=" + actualWireUsage +
                ", standardWireUsage=" + standardWireUsage +
                ", bomWireUsage=" + bomWireUsage +
                ", yield=" + yield +
                ", percents=" + percents +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                '}';
    }
}
