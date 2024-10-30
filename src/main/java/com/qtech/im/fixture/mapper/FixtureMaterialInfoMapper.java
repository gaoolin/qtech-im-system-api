package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureMaterialInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 08:32:11
 * desc   :
 */

@Repository
public interface FixtureMaterialInfoMapper {

    public List<FixtureMaterialInfo> selectFixtureMaterialInfoList(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer addFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer editFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer removeFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);
}
