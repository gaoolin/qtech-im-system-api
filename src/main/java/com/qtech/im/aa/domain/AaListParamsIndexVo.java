package com.qtech.im.aa.domain;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.common.domain.ImEqsNetworkingAndRemoteInfo;
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
public class AaListParamsIndexVo extends ImEqsNetworkingAndRemoteInfo {
    private int chkCnt;
    @Excel(name = "点检通过数")
    private int okCnt;
    @Excel(name = "点检失败数")
    private int errCnt;
    @Excel(name = "参数缺失数")
    private int lackParamsCnt;
    @Excel(name = "无模版")
    private int noModCnt;
    @Excel(name = "参数超限数")
    private int overflowCnt;
    @Excel(name = "参数异常数")
    private int unsuitableCnt;
    @Excel(name = "参数错误率")
    private float errRatio;
}
