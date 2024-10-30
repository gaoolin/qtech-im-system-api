package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureCategoryInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 09:16:11
 * desc   :
 */


public interface IFixtureCategoryInfoService {

    public FixtureCategoryInfo selectOneFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo);

    public List<FixtureCategoryInfo> selectFixtureCategoryInfoList(FixtureCategoryInfo fixtureCategoryInfo);

    public Boolean isFixtureCategoryInfoExist(FixtureCategoryInfo fixtureCategoryInfo);

    public List<FixtureCategoryInfo> selectFixtureCategoryList(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer addFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer editFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo);

    public Integer removeFixtureCategoryInfo(Long cId);

    public List<FixtureCategoryInfo> selectFixtureCategoryAll(FixtureCategoryInfo fixtureCategoryInfo);
}
