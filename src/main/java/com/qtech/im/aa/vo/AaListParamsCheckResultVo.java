package com.qtech.im.aa.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.aa.domain.parsed.AaListParamsCheckResult;
import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/05 14:45:37
 * desc   :
 */

@Data
public class AaListParamsCheckResultVo {
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
    @Excel(name = "点检时间")
    private String dt;
    @Excel(name = "状态", dictType = "aa_list_params_status")
    private Integer statusCode;
    @Excel(name = "描述")
    private String description;

    public AaListParamsCheckResultVo(AaListParamsCheckResult aaListParamsCheckResult) {
        this.factoryName = aaListParamsCheckResult.getFactoryName();
        this.groupName = aaListParamsCheckResult.getGroupName();
        this.eqId = aaListParamsCheckResult.getEqId();
        this.mcId = aaListParamsCheckResult.getMcId();
        this.prodType = aaListParamsCheckResult.getProdType();
        this.simId = aaListParamsCheckResult.getSimId();
        this.dt = aaListParamsCheckResult.getDt() == null ? "" : aaListParamsCheckResult.getDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.statusCode = aaListParamsCheckResult.getStatusCode();
        this.description = aaListParamsCheckResult.getDescription();
    }
}
