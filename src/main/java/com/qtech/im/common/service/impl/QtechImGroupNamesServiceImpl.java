package com.qtech.im.common.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.mapper.QtechImGroupNamesMapper;
import com.qtech.im.common.service.IQtechImGroupNamesService;
import com.qtech.im.config.TbQueryConditionConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 13:50:03
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class QtechImGroupNamesServiceImpl implements IQtechImGroupNamesService {

    @Autowired
    private QtechImGroupNamesMapper qtechImGroupNamesMapper;

    @Autowired
    private TbQueryConditionConfig tbQueryConditionConfig;

    @Override
    public List<ImReportBaseInfo> getHistoryGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getHistoryGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("查询数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getLatestGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getLatestGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("查询数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWireGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getWireGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWbOlpGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getWbOlpGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWbOlpLatestGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getWbOlpLatestGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @DataSource(DataSourceType.THIRD)
    @Override
    public List<ImReportBaseInfo> getEqnGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return qtechImGroupNamesMapper.getEqnGroupNames(deptNames, deviceTypes, imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @DataSource(DataSourceType.THIRD)
    @Override
    public List<ImReportBaseInfo> getQcpGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        try {
            return qtechImGroupNamesMapper.getQcpGroupNames(deptNames, deviceTypes, imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    /**
     * @param imReportBaseInfo
     * @return
     */
    @DataSource(DataSourceType.SECOND)
    @Override
    public List<ImReportBaseInfo> getAaCtrlGroupNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImGroupNamesMapper.getAaCtrlGroupNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }
}
