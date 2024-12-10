package com.qtech.im.qcp.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.config.TbQueryConditionConfig;
import com.qtech.im.qcp.domain.QcpParams;
import com.qtech.im.qcp.domain.QcpParamsDetail;
import com.qtech.im.qcp.mapper.QcpParamsMapper;
import com.qtech.im.qcp.service.IQcpParamsService;
import com.qtech.im.qcp.vo.QcpParamsDetailVo;
import com.qtech.im.qcp.vo.QcpParamsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:52:30
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.THIRD)
@Service
public class QcpParamsServiceImpl implements IQcpParamsService {

    @Autowired
    private QcpParamsMapper qcpParamsMapper;

    @Autowired
    private TbQueryConditionConfig tbQueryConditionConfig;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public QtechImVoUtil.QtechImVos<QcpParamsVo> selectQcpParamsOverviewList(QcpParams qcpParams) {
        try {
            List<String> deptNames = tbQueryConditionConfig.getDeptNames();
            List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
            ArrayList<QcpParamsVo> vos = new ArrayList<>();
            qcpParams.setDt(LocalDateTime.now());
            List<QcpParams> list = qcpParamsMapper.selectQcpParamsOverviewList(deptNames, deviceTypes, qcpParams);
            long total = new PageInfo(list).getTotal();
            if (!list.isEmpty()) {
                list.stream().map(QcpParamsVo::new).collect(Collectors.toList()).forEach(vos::add);
            }
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("查询QCP参数概览列表失败", e);
            throw new RuntimeException("查询QCP参数概览列表失败，请联系系统负责人!");
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public QtechImVoUtil.QtechImVos<QcpParamsDetailVo> selectQcpParamsList(QcpParamsDetail qcpParamsDetail) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        ArrayList<QcpParamsDetailVo> vos = new ArrayList<>();
        try {
            // FIXME: 2024/01/19 09:04:06 当长时间没有采集的数据上来时，应当给前端一个异常提示，抛出一个异常，与Qcp相关的亦复如是
            List<QcpParamsDetail> list = qcpParamsMapper.selectQcpParamsList(deptNames, deviceTypes, qcpParamsDetail);
            long total = new PageInfo(list).getTotal();
            list.stream().map(QcpParamsDetailVo::new).collect(Collectors.toList()).forEach(vos::add);
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("查询QCP参数列表失败", e);
            throw new RuntimeException("查询QCP参数列表失败，请联系系统负责人!");
        }
    }

    @Override
    public String getMaxTime() {
        try {
            return qcpParamsMapper.getMaxTime();
        } catch (Exception e) {
            log.error("获取最大时间失败", e);
            throw new RuntimeException("获取最大时间失败，请联系系统负责人!");
        }
    }

    @Override
    public boolean checkIotStatus() {
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        return qcpParamsMapper.checkIotStatus(deviceTypes);
    }
}
