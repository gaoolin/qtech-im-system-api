package com.qtech.im.wb.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.wb.domain.WbOlpPercentage;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/06 10:45:57
 * desc   :
 */

@Data
public class WbOlpPercentageVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备编号")
    private String eqId;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "机型")
    private String prodType;
    @Excel(name = "比对次数")
    private Integer computeCnt;
    @Excel(name = "正确次数")
    private Integer okCnt;
    @Excel(name = "错误次数")
    private Integer errCnt;
    @Excel(name = "错误率")
    private Float errRatio;

    public WbOlpPercentageVo(WbOlpPercentage wbOlpPercentage) {
        this.factoryName = wbOlpPercentage.getFactoryName();
        this.groupName = wbOlpPercentage.getGroupName();
        this.eqId = wbOlpPercentage.getEqId();
        this.mcId = wbOlpPercentage.getMcId();
        this.prodType = wbOlpPercentage.getProdType();
        this.computeCnt = wbOlpPercentage.getComputeCnt();
        this.okCnt = wbOlpPercentage.getOkCnt();
        this.errCnt = wbOlpPercentage.getErrCnt();
        this.errRatio = wbOlpPercentage.getErrRatio();
    }
}
