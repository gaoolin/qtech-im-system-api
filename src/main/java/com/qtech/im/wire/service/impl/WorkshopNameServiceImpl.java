package com.qtech.im.wire.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wire.domain.EqLevelWireUsage;
import com.qtech.im.wire.domain.WorkshopNameList;
import com.qtech.im.wire.mapper.WorkshopNameListMapper;
import com.qtech.im.wire.service.IWorkshopNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/27 16:08:42
 * @description :
 */
@DataSource(value= DataSourceType.SECOND)
@Service
public class WorkshopNameServiceImpl implements IWorkshopNameService {

    @Autowired
    WorkshopNameListMapper workshopNameListMapper;

    @Override
    public List<WorkshopNameList> selectWorkshopNameList(EqLevelWireUsage eqLevelWireUsage) {

        try {
            return workshopNameListMapper.selectWorkshopNameList(eqLevelWireUsage);
        } catch (Exception e) {
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人！");
        }
    }
}
