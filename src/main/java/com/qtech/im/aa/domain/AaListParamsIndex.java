package com.qtech.im.aa.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImReportBaseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 17:02:21
 * desc   :
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AaListParamsIndex extends ImReportBaseInfo {
    @Excel(name = "点检总数")
    private int chkCnt;
    @Excel(name = "点检通过数")
    private int okCnt;
    @Excel(name = "点检失败数")
    private int errCnt;
    @Excel(name = "无模版")
    private int noTplCnt;
    @Excel(name = "少参数")
    private int lackParamsCnt;
    @Excel(name = "参数值异常")
    private int unsuitableCnt;
    @Excel(name = "多参数")
    private int overflowCnt;
    @Excel(name = "复合异常")
    private int compErrCnt;
    @Excel(name = "模版离线")
    private int offlineTplCnt;
    @Excel(name = "模版明细缺失")
    private int lackTplDetailCnt;
    @Excel(name = "参数错误率")
    private float errRatio;
}
