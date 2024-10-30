package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureProdTypeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 09:22:04
 * desc   :
 */

@Repository
public interface FixtureProdTypeInfoMapper {

    public List<FixtureProdTypeInfo> selectFixtureProdTypeInfoList(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer upsertFixtureProdType(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer addFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer editFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);

    public Integer removeFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo);
}
