package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 15:44:42
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WbOlpChk extends ImReportBaseInfo {
    private Integer statusCode;
    private Integer category;
    private String flag;
}
