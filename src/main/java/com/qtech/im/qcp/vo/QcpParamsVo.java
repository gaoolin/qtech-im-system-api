package com.qtech.im.qcp.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.qcp.domain.QcpParams;
import lombok.Data;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/09 15:28:37
 * desc   :  qcp 参数
 */

@Data
public class QcpParamsVo {
    @Excel(name = "厂区")
    private String factoryName;
    @Excel(name = "车间")
    private String groupName;
    @Excel(name = "设备类型")
    private String deviceType;
    @Excel(name = "设备总数")
    private int ttlEqs;
    @Excel(name = "在线设备")
    private int onlineEqs;
    @Excel(name = "离线设备")
    private int offlineEqs;
    @Excel(name = "qcp为空")
    private int qcpParamsIsNull;
    @Excel(name = "远程未开启")
    private int remoteStatusOff;

    public QcpParamsVo(QcpParams qcpParams) {
        this.factoryName = qcpParams.getFactoryName();
        this.groupName = qcpParams.getGroupName();
        this.deviceType = qcpParams.getDeviceType();
        this.ttlEqs = qcpParams.getTtlEqs();
        this.onlineEqs = qcpParams.getOnlineEqs();
        this.offlineEqs = qcpParams.getOfflineEqs();
        this.qcpParamsIsNull = qcpParams.getQcpParamsIsNull();
        this.remoteStatusOff = qcpParams.getRemoteStatusOff();
    }
}
