package com.qtech.im.aa.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/05 15:20:13
 * desc   :
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AaListParamsEqCtrlVo {
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
    private String source;
    @Excel(name = "状态", dictType = "aa_list_params_ignore_status", defaultValue = "受控")
    @TableField("STATUS")
    private Integer statusCode;
    @Excel(name = "操作次数")
    private Integer opCnt;
    @Excel(name = "更新人")
    private String updateBy;
    @Excel(name = "更新时间")
    @TableField(value = "UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDt;
    @Excel(name = "备注")
    private String remark;
}
