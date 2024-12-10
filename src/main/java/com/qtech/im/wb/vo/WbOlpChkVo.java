package com.qtech.im.wb.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.wb.domain.WbOlpChk;
import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/06 10:01:42
 * desc   :
 */

@Data
public class WbOlpChkVo {
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
    @Excel(name = "时间")
    private String dt;
    @Excel(name = "状态", dictType = "comparison_result_code")
    private Integer statusCode;
    @Excel(name = "描述")
    private String description;

    public WbOlpChkVo(WbOlpChk wbOlpChk) {
        this.factoryName = wbOlpChk.getFactoryName();
        this.groupName = wbOlpChk.getGroupName();
        this.eqId = wbOlpChk.getEqId();
        this.mcId = wbOlpChk.getMcId();
        this.prodType = wbOlpChk.getProdType();
        this.simId = wbOlpChk.getSimId();
        this.dt = wbOlpChk.getDt() == null ? "" : wbOlpChk.getDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.statusCode = wbOlpChk.getStatusCode();
        this.description = wbOlpChk.getDescription();
    }
}
