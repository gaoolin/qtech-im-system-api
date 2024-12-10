package com.qtech.im.eqn.vo;

import com.qtech.framework.aspectj.lang.annotation.Excel;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
import lombok.Data;

import java.time.format.DateTimeFormatter;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/25 09:54:55
 * desc   :
 */

@Data
public class ImEqsNetAndRemoteInfoVo {
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
    private String simId;
    @Excel(name = "最后采集时间")
    private String dt;
    @Excel(name = "联网状态", readConverterExp = "0=离线,1=在线")
    private Integer netStatus;
    @Excel(name = "远程状态", readConverterExp = "0=关闭,1=开启")
    private Integer remoteStatus;

    public ImEqsNetAndRemoteInfoVo(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        this.factoryName = imEqsNetAndRemoteInfo.getFactoryName();
        this.groupName = imEqsNetAndRemoteInfo.getGroupName();
        this.deviceType = imEqsNetAndRemoteInfo.getDeviceType();
        this.eqId = imEqsNetAndRemoteInfo.getEqId();
        this.mcId = imEqsNetAndRemoteInfo.getMcId();
        this.simId = imEqsNetAndRemoteInfo.getSimId();
        this.dt = imEqsNetAndRemoteInfo.getDt() == null ? null : imEqsNetAndRemoteInfo.getDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.netStatus = imEqsNetAndRemoteInfo.getNetStatus();
        this.remoteStatus = imEqsNetAndRemoteInfo.getRemoteStatus();
    }
}