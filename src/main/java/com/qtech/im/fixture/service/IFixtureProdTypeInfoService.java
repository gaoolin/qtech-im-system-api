package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureProdTypeInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 09:32:59
 * desc   :
 */


public interface IFixtureProdTypeInfoService {

    public FixtureProdTypeInfo selectOneFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public List<FixtureProdTypeInfo> selectFixtureProdTypeInfoList(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Boolean isFixtureProdTypeInfoExist(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer addFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer editFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer removeFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Boolean isSecureDeletion(FixtureProdTypeInfo fixtureProdTypeInfo);
}
