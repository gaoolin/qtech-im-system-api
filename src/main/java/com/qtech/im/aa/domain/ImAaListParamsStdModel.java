package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName IM_AA_LIST_PARAMS_STD_MODEL
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="IM_AA_LIST_PARAMS_STD_MODEL")
@Data
public class ImAaListParamsStdModel extends ImAaListParamsForMybatisPlus {
    private Long id;
    private String prodType;

    @TableField(exist = false)
    private static final long serialVersionUID = 529L;
}