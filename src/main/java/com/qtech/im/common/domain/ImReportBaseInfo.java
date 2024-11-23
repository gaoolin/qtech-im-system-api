package com.qtech.im.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 15:56:23
 * desc   :  报表开发基础类
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ImReportBaseInfo extends BaseEntity {
    private Long id;
    @Excel(name = "厂区")
    private String factoryName;  // 工厂名称
    @Excel(name = "车间")
    private String groupName;  // 所属车间
    @Excel(name = "站位")
    private String deptName;    // 所属部门
    @Excel(name = "设备类型")
    private String deviceType;  // 设备类型
    @Excel(name = "设备编号")
    private String eqId;  // 设备ID
    @Excel(name = "机台号")
    private String mcId;  // 机台ID
    @Excel(name = "机型")
    private String prodType; // 机型
    @Excel(name = "盒子号")
    private String simId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间")
    private LocalDateTime dt;
    /** 默认值 null */
    @Excel(name = "代码")
    private Integer code;
    @Excel(name = "描述")
    private String description;
}
