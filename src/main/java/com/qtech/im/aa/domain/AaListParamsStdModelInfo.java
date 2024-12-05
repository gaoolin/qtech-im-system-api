package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:37:00
 * desc   :
 */

@Data
@TableName(value = "IMBIZ.IM_AA_LIST_PARAMS_STD_MODEL_INFO")
public class AaListParamsStdModelInfo extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String prodType;
    private Integer listParams;
    private Integer itemParams;
    private Integer status;
    private String provider;
    private String belongTo;
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
    @TableField(exist = false)
    private String searchValue;
}
