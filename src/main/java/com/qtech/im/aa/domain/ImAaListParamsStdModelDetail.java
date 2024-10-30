package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName IM_AA_LIST_PARAMS_STD_MODEL
 */
@TableName(value ="IM_AA_LIST_PARAMS_STD_MODEL")
@Data
public class ImAaListParamsStdModelDetail extends ImAaListParams {
    private Long id;
    private String prodType;

    @TableField(exist = false)
    private static final long serialVersionUID = 529L;
}