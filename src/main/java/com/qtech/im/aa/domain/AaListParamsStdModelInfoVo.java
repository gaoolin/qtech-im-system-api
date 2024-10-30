package com.qtech.im.aa.domain;

import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 13:37:00
 * desc   :
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class AaListParamsStdModelInfoVo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String prodType;
    private Integer listParams;
    private Integer itemParams;
    private Integer status;
    private String provider;
    private String belongTo;
}
