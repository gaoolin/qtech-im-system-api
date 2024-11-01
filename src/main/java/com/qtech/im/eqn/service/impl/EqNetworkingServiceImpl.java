package com.qtech.im.eqn.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
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

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        try {
            return eqNetworkingMapper.selectEqNetworkingList(imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> getFactoryNames() {
        try {
            return eqNetworkingMapper.getFactoryNames();
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> getWorkshopNames(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        try {
            return eqNetworkingMapper.getWorkshopNames(imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingOfflineList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        try {
            return eqNetworkingMapper.selectEqNetworkingOfflineList(imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> selectEqNetworkingAgg(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        try {
            return eqNetworkingMapper.selectEqNetworkingAgg(imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}