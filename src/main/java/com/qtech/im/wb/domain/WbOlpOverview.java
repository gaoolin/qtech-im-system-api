package com.qtech.im.wb.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/04 15:18:41
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WbOlpOverview extends ImReportBaseInfo {
    @Excel(name = "比对次数")
    private int computeCnt;

    @Excel(name = "正确次数")
    private int okCnt;

    @Excel(name = "错误次数")
    private int errCnt;

    @Excel(name = "金线偏移")
    private int offsetCnt;

    @Excel(name = "无程序")
    private int npCnt;

    @Excel(name = "少线")
    private int lackCnt;

    @Excel(name = "多线")
    private int overCnt;

    @Excel(name = "错误率")
    private float errRatio;
}
