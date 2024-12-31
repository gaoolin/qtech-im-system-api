package com.qtech.im.aa.domain.ctrl;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/11 14:30:11
 * desc   :
 */

@Data
@TableName("qtech_eq_ads.im_eq_reverse_ignore_info")
public class AaListParamsEqCtrl {
    private String simId;
    private String source;
    @TableField("STATUS")
    private Integer statusCode;
    private Integer opCnt;
    private String updateBy;
    @TableField(value = "UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDt;
    private String remark;
}
