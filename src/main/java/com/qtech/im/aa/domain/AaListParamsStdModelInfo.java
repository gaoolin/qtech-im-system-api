package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

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
    @JsonInclude
    private String searchValue;


    // 重写equals和hashCode方法，用于判断对象的对应属性是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AaListParamsStdModelInfo that = (AaListParamsStdModelInfo) o;
        return Objects.equals(prodType, that.prodType) &&
                Objects.equals(listParams, that.listParams) &&
                Objects.equals(itemParams, that.itemParams) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodType, listParams, itemParams, status);
    }
}
