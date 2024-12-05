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
public class AaListParamsEq extends ImReportBaseInfo {
    private Integer statusCode;
    private String source;
    private Integer opCnt;
    private String updateBy;
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
