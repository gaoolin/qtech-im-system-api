package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.share.aa.pojo.ImAaListParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.*;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/03 08:54:38
 * desc   :
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName(value = "IMBIZ.IM_AA_LIST_PARAMS_STD_MODEL")
@Data
public class AaListParamsStdModel extends ImAaListParams {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String prodType;

    // 业务字段
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    /**
     * 请求参数
     */
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
