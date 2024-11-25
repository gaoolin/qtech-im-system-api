package com.qtech.im.common.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.mapper.QtechImEqsInfoMapper;
import com.qtech.im.common.service.IQtechImEqsInfoService;
import com.qtech.im.config.TbQueryConditionConfig;
import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 14:10:33
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class QtechImEqsInfoServiceImpl implements IQtechImEqsInfoService {

    @Autowired
    private QtechImEqsInfoMapper qtechImEqsInfoMapper;

    @Autowired
    private TbQueryConditionConfig tbQueryConditionConfig;

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> listEqsInfo(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return qtechImEqsInfoMapper.listEqsInfo(deptNames, deviceTypes, imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人！");
        }
    }

    @DataSource(DataSourceType.THIRD)
    @Override
    public Boolean iotQcpStatus(ImReportBaseInfo imReportBaseInfo) {
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        return qtechImEqsInfoMapper.iotQcpStatus(deviceTypes, imReportBaseInfo);
    }

    @Override
    public Boolean iotAaStatus(ImReportBaseInfo imReportBaseInfo) {
        return qtechImEqsInfoMapper.iotAaStatus(imReportBaseInfo);
    }
}
