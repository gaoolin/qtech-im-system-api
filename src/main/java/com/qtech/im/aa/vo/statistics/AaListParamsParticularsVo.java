package com.qtech.im.aa.vo.statistics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/16 11:43:51
 * desc   :
 */

@Data
public class AaListParamsParticularsVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备编码")
    private String eqId;
    @Excel(name = "SIM卡号")
    private String simId;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "机型")
    private String prodType;
    @Excel(name = "点检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime chkDt;
    @Excel(name = "状态", readConverterExp = "0=正常,1=无模版,2=少参数,3=参数值异常,4=多参数,5=复合异常,6=模版离线,7=模版明细缺失")
    private Integer statusCode;
    @Excel(name = "描述")
    private String description;
    @TableField(exist = false)
    private Map<String, Object> params;
    @TableField(exist = false)
    private String flag;
}
