package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtech.share.aa.pojo.ImAaListStdTemplateInfo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:37:00
 * desc   :
 */
@Data
@ToString(callSuper = true)
@TableName(value = "IMBIZ.IM_AA_LIST_PARAMS_STD_MODEL_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AaListParamsStdTemplateInfo extends ImAaListStdTemplateInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JsonIgnore
    private String provider;
    @JsonIgnore
    private String belongTo;
    @JsonIgnore
    private String createBy;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private String updateBy;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private String remark;
    @TableField(exist = false)
    @JsonIgnore
    private String searchValue;
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}