package com.qtech.im.eqn.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImEqsNetCnt;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.config.TbQueryConditionConfig;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
import com.qtech.im.eqn.mapper.EqNetworkingMapper;
import com.qtech.im.eqn.service.IEqNetworkingService;
import com.qtech.im.eqn.vo.ImEqsNetAndRemoteInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<ImEqsNetAndRemoteInfoVo> list(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        try {
            List<ImEqsNetAndRemoteInfoVo> vos = null;
            String deptName = tbQueryConditionConfig.getDeptName();
            List<String> deptNames = tbQueryConditionConfig.getDeptNames();
            List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
            List<ImEqsNetAndRemoteInfo> list = eqNetworkingMapper.selectEqNetworkingList(deptNames, deviceTypes, imEqsNetAndRemoteInfo);
            long total = new PageInfo(list).getTotal();
            if (CollectionUtils.isNotEmpty(list)) {
                vos = list.stream().map(ImEqsNetAndRemoteInfoVo::new).collect(Collectors.toList());
            }
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetAndRemoteInfo> offlineList(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return eqNetworkingMapper.selectEqNetworkingOfflineList(deptNames, deviceTypes, imEqsNetAndRemoteInfo);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImEqsNetCnt> agg(ImEqsNetCnt imEqsNetCnt) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return eqNetworkingMapper.selectEqNetworkingAgg(deptNames, deviceTypes, imEqsNetCnt);
        } catch (Exception e) {
            log.error("查询数据库失败", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}