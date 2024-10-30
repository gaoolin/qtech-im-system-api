package com.qtech.im.fixture.mapper;

import com.qtech.im.fixture.domain.FixtureCategoryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 08:58:42
 * desc   :
 */

@Repository
public interface FixtureCategoryInfoMapper {

    public List<FixtureCategoryInfo> selectFixtureCategoryInfoList(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer addFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer editFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer removeFixtureCategoryInfo(Long cId);
}
