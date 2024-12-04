package com.qtech.im.aa.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:37:00
 * desc   :
 */

@Data
@TableName(value = "IMBIZ.IM_AA_LIST_PARAMS_STD_MODEL_INFO")
public class AaListParamsStdModelInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String prodType;
    private Integer listParams;
    private Integer itemParams;
    private Integer status;
    private String provider;
    private String belongTo;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
