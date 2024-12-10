package com.qtech.im.qcp.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.qcp.domain.QcpParamsDetail;
import lombok.Data;

import java.io.Serializable;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/09 15:46:09
 * desc   :  qcp明细
 */

@Data
public class QcpParamsDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备类型")
    private String deviceType;
    @Excel(name = "设备编号")
    private String eqId;
    @Excel(name = "机台号")
    private String mcId;
    @Excel(name = "盒子号")
    private String prodType;
    @Excel(name = "描述")
    private String description;

    public QcpParamsDetailVo(QcpParamsDetail qcpParamsDetail) {
        this.factoryName = qcpParamsDetail.getFactoryName();
        this.groupName = qcpParamsDetail.getGroupName();
        this.deviceType = qcpParamsDetail.getDeviceType();
        this.eqId = qcpParamsDetail.getEqId();
        this.mcId = qcpParamsDetail.getMcId();
        this.prodType = qcpParamsDetail.getProdType();
        this.description = qcpParamsDetail.getDescription();
    }
}
