package com.qtech.im.aa.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.aa.domain.AaListParamsEq;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/05 15:20:13
 * desc   :
 */

@Data
public class AaListParamsEqVo {
    private Long id;
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备编号")
    private String eqId;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "机型")
    private String prodType;
    @Excel(name = "盒子号")
    private String simId;
    @Excel(name = "状态", dictType = "aa_list_params_ignore_status", defaultValue = "受控")
    private Integer statusCode;
    private String source;
    @Excel(name = "操作次数")
    private Integer opCnt;
    @Excel(name = "创建人")
    private String createBy;
    @Excel(name = "创建时间")
    private String createTime;
    @Excel(name = "更新人")
    private String updateBy;
    @Excel(name = "更新时间")
    private String updateTime;
    @Excel(name = "备注")
    private String remark;

    public AaListParamsEqVo(AaListParamsEq aaListParamsEq) {
        this.id = aaListParamsEq.getId();
        this.factoryName = aaListParamsEq.getFactoryName();
        this.groupName = aaListParamsEq.getGroupName();
        this.eqId = aaListParamsEq.getEqId();
        this.mcId = aaListParamsEq.getMcId();
        this.prodType = aaListParamsEq.getProdType();
        this.simId = aaListParamsEq.getSimId();
        this.statusCode = aaListParamsEq.getStatusCode();
        this.source = aaListParamsEq.getSource();
        this.opCnt = aaListParamsEq.getOpCnt();
        this.createBy = aaListParamsEq.getCreateBy();
        this.createTime = aaListParamsEq.getCreateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsEq.getCreateTime());
        this.updateBy = aaListParamsEq.getUpdateBy();
        this.updateTime = aaListParamsEq.getUpdateTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aaListParamsEq.getUpdateTime());
        this.remark = aaListParamsEq.getRemark();
    }
}
