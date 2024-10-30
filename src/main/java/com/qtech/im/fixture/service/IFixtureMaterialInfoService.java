package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureMaterialInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 08:54:05
 * desc   :
 */


public interface IFixtureMaterialInfoService {

    public FixtureMaterialInfo selectOneFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);

    public List<FixtureMaterialInfo> selectFixtureMaterialInfoList(FixtureMaterialInfo fixtureMaterialInfo);

    public Boolean isFixtureMaterialInfoExist(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer addFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer editFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);

    public Integer removeFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo);
}
