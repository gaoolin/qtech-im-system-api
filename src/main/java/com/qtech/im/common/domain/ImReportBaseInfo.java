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
    private String factoryName;  // 工厂名称
    private String groupName;  // 所属车间
    private String deptName;    // 所属部门
    private String deviceType;  // 设备类型
    private String eqId;  // 设备ID
    private String mcId;  // 机台ID
    private String prodType; // 机型
    private String simId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;
    /** 默认值 null */
    private Integer statusCode;
    private String status;
    private Long amount;
    private String description;
}