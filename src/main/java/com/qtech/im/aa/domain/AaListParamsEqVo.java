package com.qtech.im.aa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.util.Date;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:07:05
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AaListParamsEqVo extends ImReportBaseInfo {
    @Excel(name = "状态", dictType = "aa_list_params_ignore_status", defaultValue = "受控")
    private Integer status;
    private String source;
    @Excel(name = "操作次数")
    private Integer opCnt;
    @Excel(name = "操作人")
    private String updateBy;
    @Excel(name = "上次操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    static class IntegerToStringSerializer extends JsonSerializer<Integer> {
        @Override
        public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            // gen.writeString("prefix_" + value + "_suffix");
            gen.writeString(value + "");
        }
    }
}
