package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/15 13:19:15
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WbOlpPercentageVo extends ImReportBaseInfo {
    @Excel(name = "比对次数")
    private int computeCnt;

    @Excel(name = "正确次数")
    private int okCnt;

    @Excel(name = "错误次数")
    private int errCnt;

    @Excel(name = "错误率")
    private float errRatio;

    @Excel(name = "状态", dictType = "comparison_result_code")
    private Integer status;

    private String flag;
}
