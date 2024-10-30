package com.qtech.im.wb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/08 14:34:09
 * desc   :
 */

/** Mybatis对基础数据类型默认值为0，对象（包装类）默认值为null，布尔值为false */
public class WbOlpStdModUpload extends BaseEntity {

    @Excel(name = "盒子号")
    private String simId;

    @Excel(name = "机种")
    private String mcId;

    @Excel(name = "时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dt;

    @Excel(name = "线号")
    private Integer lineNo;

    @Excel(name = "leadX")
    private Double leadX;

    @Excel(name = "leadY")
    private Double leadY;

    @Excel(name = "padX")
    private Double padX;

    @Excel(name = "padY")
    private Double padY;

    @Excel(name = "lead点间距")
    private Double leadDiff;

    @Excel(name = "pad间距")
    private Double padDiff;

    @Excel(name = "线长")
    private Double wireLen;

    @Excel(name = "checkPort")
    private Integer checkPort;

    @Excel(name = "产品序号")
    private Integer pId;

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getMcId() {
        return mcId;
    }

    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Double getLeadX() {
        return leadX;
    }

    public void setLeadX(Double leadX) {
        this.leadX = leadX;
    }

    public Double getLeadY() {
        return leadY;
    }

    public void setLeadY(Double leadY) {
        this.leadY = leadY;
    }

    public Double getPadX() {
        return padX;
    }

    public void setPadX(Double padX) {
        this.padX = padX;
    }

    public Double getPadY() {
        return padY;
    }

    public void setPadY(Double padY) {
        this.padY = padY;
    }

    public Double getLeadDiff() {
        return leadDiff;
    }

    public void setLeadDiff(Double leadDiff) {
        this.leadDiff = leadDiff;
    }

    public Double getPadDiff() {
        return padDiff;
    }

    public void setPadDiff(Double padDiff) {
        this.padDiff = padDiff;
    }

    public Double getWireLen() {
        return wireLen;
    }

    public void setWireLen(Double wireLen) {
        this.wireLen = wireLen;
    }

    public Integer getCheckPort() {
        return checkPort;
    }

    public void setCheckPort(Integer checkPort) {
        this.checkPort = checkPort;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "WbOlpStdModUpload{" +
                "simId='" + simId + '\'' +
                ", mcId='" + mcId + '\'' +
                ", dt=" + dt +
                ", lineNo=" + lineNo +
                ", leadX=" + leadX +
                ", leadY=" + leadY +
                ", padX=" + padX +
                ", padY=" + padY +
                ", leadDiff=" + leadDiff +
                ", padDiff=" + padDiff +
                ", wireLen=" + wireLen +
                ", checkPort=" + checkPort +
                ", pId=" + pId +
                '}';
    }
}
