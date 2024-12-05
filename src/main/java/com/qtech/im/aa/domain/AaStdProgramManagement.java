package com.qtech.im.aa.domain;

import com.qtech.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/02 15:08:52
 * desc   :
 */


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AaStdProgramManagement extends BaseEntity {
    private Long id;
    private String prodType;
    private String fileName;
    private String newFileName;
    private String version;
    private String size;
    private Integer status;
    private Long downloadCnt;
    private String factoryName;
    private Integer flag;
}
