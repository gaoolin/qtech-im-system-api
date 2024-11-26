package com.qtech.im.eqn.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.config.TbQueryConditionConfig;
import com.qtech.im.eqn.domain.ImEqsAndNetCntVo;
import com.qtech.im.eqn.mapper.EqNetworkingMapper;
import com.qtech.im.eqn.service.IEqNetworkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:44:37
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.THIRD)
@Service
public class EqNetworkingServiceImpl implements IEqNetworkingService {

    @Autowired
    private EqNetworkingMapper eqNetworkingMapper;

    @Autowired
    private TbQueryConditionConfig tbQueryConditionConfig;

    @Override
    public List<ImEqsAndNetCntVo> selectEqNetworkingList(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo) {
        String deptName = tbQueryConditionConfig.getDeptName();
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();

        try {
            return eqNetworkingMapper.selectEqNetworkingList(deptNames, deviceTypes, imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsAndNetCntVo> selectEqNetworkingOfflineList(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return eqNetworkingMapper.selectEqNetworkingOfflineList(deptNames, deviceTypes, imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsAndNetCntVo> selectEqNetworkingAgg(ImEqsAndNetCntVo imEqsNetworkingAndRemoteInfoVo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return eqNetworkingMapper.selectEqNetworkingAgg(deptNames, deviceTypes, imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}