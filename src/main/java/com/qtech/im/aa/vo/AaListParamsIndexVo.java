package com.qtech.im.aa.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.aa.domain.AaListParamsIndex;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/05 15:43:35
 * desc   :
 */

@Data
public class AaListParamsIndexVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "点检总次数")
    private Integer chkCnt;
    @Excel(name = "点检通过数")
    private Integer okCnt;
    @Excel(name = "点检失败数")
    private Integer errCnt;
    @Excel(name = "无模版")
    private Integer noTplCnt;
    @Excel(name = "少参数")
    private Integer lackParamsCnt;
    @Excel(name = "参数值异常")
    private Integer unsuitableCnt;
    @Excel(name = "多参数")
    private Integer overflowCnt;
    @Excel(name = "复合异常")
    private Integer compErrCnt;
    @Excel(name = "模版离线")
    private Integer offlineTplCnt;
    @Excel(name = "模版明细缺失")
    private Integer lackTplDetailCnt;
    @Excel(name = "参数错误率")
    private float errRatio;

    public AaListParamsIndexVo(AaListParamsIndex aaListParamsIndex) {
        this.factoryName = aaListParamsIndex.getFactoryName();
        this.groupName = aaListParamsIndex.getGroupName();
        this.chkCnt = aaListParamsIndex.getChkCnt();
        this.okCnt = aaListParamsIndex.getOkCnt();
        this.errCnt = aaListParamsIndex.getErrCnt();
        this.noTplCnt = aaListParamsIndex.getNoTplCnt();
        this.lackParamsCnt = aaListParamsIndex.getLackParamsCnt();
        this.unsuitableCnt = aaListParamsIndex.getUnsuitableCnt();
        this.overflowCnt = aaListParamsIndex.getOverflowCnt();
        this.compErrCnt = aaListParamsIndex.getCompErrCnt();
        this.offlineTplCnt = aaListParamsIndex.getOfflineTplCnt();
        this.lackTplDetailCnt = aaListParamsIndex.getLackTplDetailCnt();
        this.errRatio = aaListParamsIndex.getErrRatio();
    }
}
