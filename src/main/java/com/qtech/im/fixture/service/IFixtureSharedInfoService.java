package com.qtech.im.fixture.service;

import com.qtech.im.fixture.domain.FixtureSharedInfo;

import java.util.List;
import java.util.Map;

/**
 * pogopin治具Service接口
 *
 * @author gaozhilin
 * @date 2023-06-27
 */
public interface IFixtureSharedInfoService
{
    /**
     * 查询pogopin治具
     *
     * @param fixtureSharedInfo pogopin治具主键
     * @return pogopin治具
     */
    public FixtureSharedInfo selectFixtureSharedInfoOne(FixtureSharedInfo fixtureSharedInfo);

    public FixtureSharedInfo selectFixtureSharedInfoById(Long id);

    /**
     * 查询pogopin治具列表
     *
     * @param fixtureSharedInfo pogopin治具
     * @return pogopin治具集合
     */
    public List<FixtureSharedInfo> selectFixtureSharedInfoList(FixtureSharedInfo fixtureSharedInfo);

    /**
     * 新增pogopin治具
     *
     * @param fixtureSharedInfo pogopin治具
     * @return 结果
     */
    public int addFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo);

    /**
     * 修改pogopin治具
     *
     * @param fixtureSharedInfo pogopin治具
     * @return 结果
     */
    public int editFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo);

    /**
     * 删除pogopin治具信息
     *
     * @param
     * @return 结果
     */
    public int deleteFixtureSharedInfo(FixtureSharedInfo fixtureSharedInfo);

    public List<String> selectFixtureSharedInfoDeptIds(Long userDeptId);

    public Map<String, String> uploadFixtureInfo(List<FixtureSharedInfo> fixtureSharedInfos);
}
