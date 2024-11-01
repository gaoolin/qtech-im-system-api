package com.qtech.im.common.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.mapper.QtechImFactoryNamesMapper;
import com.qtech.im.common.service.IQtechImFactoryNamesService;
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

    @Override
    public List<ImReportBaseInfo> getHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImFactoryNamesMapper.getHistoryFactoryNames(imReportBaseInfo);
        } catch (Exception e) {
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImReportBaseInfo> getLatestFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImFactoryNamesMapper.getLatestFactoryNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<ImReportBaseInfo> getWireFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        try {
            return qtechImFactoryNamesMapper.getWireFactoryNames(imReportBaseInfo);
        } catch (Exception e) {
            log.error("查询数据库失败" , e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }
}
