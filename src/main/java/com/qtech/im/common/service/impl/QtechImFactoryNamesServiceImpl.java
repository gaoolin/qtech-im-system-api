package com.qtech.im.common.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.mapper.QtechImFactoryNamesMapper;
import com.qtech.im.common.service.IQtechImFactoryNamesService;
import com.qtech.im.config.TbQueryConditionConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 16:04:52
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class QtechImFactoryNamesServiceImpl implements IQtechImFactoryNamesService {
    @Autowired
    private QtechImFactoryNamesMapper qtechImFactoryNamesMapper;

    @Autowired
    private TbQueryConditionConfig tbQueryConditionConfig;

    @Override
    public List<ImReportBaseInfo> getHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();
        try {
            return qtechImFactoryNamesMapper.getHistoryFactoryNames(imReportBaseInfo, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImReportBaseInfo> getLatestFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();
        try {
            return qtechImFactoryNamesMapper.getLatestFactoryNames(imReportBaseInfo, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWireFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();
        try {
            return qtechImFactoryNamesMapper.getWireFactoryNames(imReportBaseInfo, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWbOlpFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();
        try {
            return qtechImFactoryNamesMapper.getWbOlpFactoryNames(imReportBaseInfo, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWbOlpLatestFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();
        try {
            return qtechImFactoryNamesMapper.getWbOlpLatestFactoryNames(imReportBaseInfo, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @DataSource(DataSourceType.THIRD)
    @Override
    public List<ImReportBaseInfo> getEqnFactoryNames() {
        List<String> deptNames = tbQueryConditionConfig.getDeptNames();
        List<String> deviceTypes = tbQueryConditionConfig.getDeviceTypes();
        String sortFactoryNameCaseStatement = tbQueryConditionConfig.getSortFactoryNameCaseStatement();

        try {
            return qtechImFactoryNamesMapper.getEqnFactoryNames(deptNames, deviceTypes, sortFactoryNameCaseStatement);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @DataSource(DataSourceType.THIRD)
    @Override
    public List<ImReportBaseInfo> getQcpFactoryNames() {
        try {
            return qtechImFactoryNamesMapper.getQcpFactoryNames();
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }
}
