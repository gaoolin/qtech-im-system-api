package com.qtech.im.aa.vo.statistics;

import com.baomidou.mybatisplus.annotation.TableField;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/13 10:23:30
 * desc   :
 */

@Data
public class AaListParamsPercentageVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备编码")
    private String eqId;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "机型")
    private String prodType;
    private Integer statusCode;
    @Excel(name = "点检次数")
    private Integer totalCnt;
    @Excel(name = "OK次数")
    private Integer okCnt;
    @Excel(name = "NG次数")
    private Integer ngCnt;
    @Excel(name = "合格率")
    private Float errRatio;
    @TableField(exist = false)
    @Excel(name = "时间")
    private LocalDateTime dt;
    @TableField(exist = false)
    private Map<String, Object> params;
    private String flag;
}
