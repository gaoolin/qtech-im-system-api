package com.qtech.im.wire.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 厂区级金线用量监控对象 factory_level_wire_usage
 *
 * @author qtech
 * @date 2023-05-17
 */
public class FactoryLevelWireUsage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 厂区 */
    @Excel(name = "厂区")
    private String factoryName;

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

    /** BOM用量 */
    @Excel(name = "BOM用量")
    private Double bomWireUsage;

    /** 产量 */
    @Excel(name = "产量")
    private Long yield;

    /** 差异 */
    @Excel(name = "差异")
    private Double percents;

    /** 日期 */
    private Date createDate;

    /** 状态 */
    private String status;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
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

    public Long getYield() {
        return yield;
    }

    public void setYield(Long yield) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FactoryLevelWireUsage{" +
                "factoryName='" + factoryName + '\'' +
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
