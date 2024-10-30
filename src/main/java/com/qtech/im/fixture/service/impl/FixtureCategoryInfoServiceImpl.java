package com.qtech.im.fixture.service.impl;

import com.qtech.common.utils.DateUtils;
import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.domain.FixtureCategoryInfo;
import com.qtech.im.fixture.mapper.FixtureCategoryInfoMapper;
import com.qtech.im.fixture.service.IFixtureCategoryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 09:19:08
 * desc   :
 */

@Slf4j
@Service
public class FixtureCategoryInfoServiceImpl implements IFixtureCategoryInfoService {

    @Autowired
    FixtureCategoryInfoMapper fixtureCategoryInfoMapper;

    @DataScope(deptAlias = "ta")
    @Override
    public FixtureCategoryInfo selectOneFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo) {
        List<FixtureCategoryInfo> list = fixtureCategoryInfoMapper.selectFixtureCategoryInfoList(fixtureCategoryInfo);

        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                throw new TooManyResultsException(String.format("Expected one result (or null) to be returned by selectOne(), but found: %s", size));
            }
            return list.get(0);
        }
        return null;
    }

    @DataScope(deptAlias = "ta")
    @Override
    public List<FixtureCategoryInfo> selectFixtureCategoryInfoList(FixtureCategoryInfo fixtureCategoryInfo) {
        try {
            return fixtureCategoryInfoMapper.selectFixtureCategoryInfoList(fixtureCategoryInfo);
        } catch (Exception e) {
            log.error("selectFixtureCategoryInfoList error", e);
            throw new RuntimeException("查询出错，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Boolean isFixtureCategoryInfoExist(FixtureCategoryInfo fixtureCategoryInfo) {
        try {
            List<FixtureCategoryInfo> fixtureCategoryInfos = fixtureCategoryInfoMapper.selectFixtureCategoryInfoList(fixtureCategoryInfo);
            return !fixtureCategoryInfos.isEmpty();
        } catch (Exception e) {
            log.error("isFixtureCategoryInfoExist error", e);
            throw new RuntimeException("查询出错，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public List<FixtureCategoryInfo> selectFixtureCategoryList(FixtureCategoryInfo fixtureCategoryInfo) {
        try {
            return fixtureCategoryInfoMapper.selectFixtureCategoryInfoList(fixtureCategoryInfo);
        } catch (Exception e) {
            log.error("selectFixtureCategoryList error", e);
            throw new RuntimeException("查询出错，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer addFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo) {
        fixtureCategoryInfo.setCreateBy(getLoginUser().getUser().getNickName());
        fixtureCategoryInfo.setCreateTime(DateUtils.getNowDate());
        Integer n;
        try {
            n = fixtureCategoryInfoMapper.addFixtureCategoryInfo(fixtureCategoryInfo);
        } catch (Exception e) {
            log.error("addFixtureCategoryInfo error", e);
            throw new IllegalStateException(String.format("治具类型已存在：%s！", fixtureCategoryInfo.getFixtureCategory()));
        }
        return n;
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer editFixtureCategoryInfo(FixtureCategoryInfo fixtureCategoryInfo) {
        try {
            fixtureCategoryInfo.setUpdateBy(getLoginUser().getUser().getNickName());
            fixtureCategoryInfo.setUpdateTime(DateUtils.getNowDate());
            return fixtureCategoryInfoMapper.editFixtureCategoryInfo(fixtureCategoryInfo);
        } catch (Exception e) {
            log.error("editFixtureCategoryInfo error", e);
            throw new RuntimeException("修改出错，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer removeFixtureCategoryInfo(Long cId) {
        try {
            return fixtureCategoryInfoMapper.removeFixtureCategoryInfo(cId);
        } catch (Exception e) {
            log.error("removeFixtureCategoryInfo error", e);
            throw new RuntimeException("删除出错，请联系系统负责人！");
        }
    }

    @Override
    public List<FixtureCategoryInfo> selectFixtureCategoryAll(FixtureCategoryInfo fixtureCategoryInfo) {
        try {
            return fixtureCategoryInfoMapper.selectFixtureCategoryInfoList(fixtureCategoryInfo);
        } catch (Exception e) {
            log.error("selectFixtureCategoryAll error", e);
            throw new RuntimeException("查询出错，请联系系统负责人！");
        }
    }
}
