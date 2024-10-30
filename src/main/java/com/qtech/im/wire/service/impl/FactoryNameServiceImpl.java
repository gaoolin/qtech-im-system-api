package com.qtech.im.wire.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wire.domain.FactoryNameList;
import com.qtech.im.wire.mapper.FactoryNameListMapper;
import com.qtech.im.wire.service.IFactoryNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/27 14:05:53
 * @description :
 */
@DataSource(value = DataSourceType.SECOND)
@Service
public class FactoryNameServiceImpl implements IFactoryNameService {

    @Autowired
    FactoryNameListMapper factoryNameListMapper;

    @Override
    public List<FactoryNameList> SelectFactoryNameList() {
        return factoryNameListMapper.selectFactoryName();
    }
}
