package com.qtech.im.aa.domain.parsed;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.share.aa.pojo.ImAaListParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.*;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 16:30:04
 * desc   :
 */

@ApiModel(value = "AA List参数解析对象", description = "AA List参数解析结果表")
@Data
@Accessors(chain = true)  // 与父类返回类型保持一致
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("qtech_eq_dwd.im_aa_list_parsed_detail")
public class AaListParamsParsed extends ImAaListParams {
    @Excel(name = "盒子号")
    private String simId;

    @Excel(name = "机型")
    private String prodType;

    @Excel(name = "接收时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivedTime;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}