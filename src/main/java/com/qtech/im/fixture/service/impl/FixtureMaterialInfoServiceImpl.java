package com.qtech.im.fixture.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.domain.FixtureMaterialInfo;
import com.qtech.im.fixture.mapper.FixtureMaterialInfoMapper;
import com.qtech.im.fixture.service.IFixtureMaterialInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 08:56:11
 * desc   :
 */

@Slf4j
@Service
public class FixtureMaterialInfoServiceImpl implements IFixtureMaterialInfoService {

    @Autowired
    FixtureMaterialInfoMapper fixtureMaterialInfoMapper;

    @Override
    public FixtureMaterialInfo selectOneFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo) {
        List<FixtureMaterialInfo> list = fixtureMaterialInfoMapper.selectFixtureMaterialInfoList(fixtureMaterialInfo);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                throw new TooManyResultsException(String.format("Expected one result (or null) to be returned by selectOne(), but found: %s" , size));
            }
            return list.get(0);
        }
        return null;
    }

    @DataScope(deptAlias = "ta")
    @Override
    public List<FixtureMaterialInfo> selectFixtureMaterialInfoList(FixtureMaterialInfo fixtureMaterialInfo) {
        try {
            return fixtureMaterialInfoMapper.selectFixtureMaterialInfoList(fixtureMaterialInfo);
        } catch (Exception e) {
            log.error("查询FixtureMaterialInfo列表失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Boolean isFixtureMaterialInfoExist(FixtureMaterialInfo fixtureMaterialInfo) {
        try {
            List<FixtureMaterialInfo> fixtureMaterialInfos = fixtureMaterialInfoMapper.selectFixtureMaterialInfoList(fixtureMaterialInfo);
            return !fixtureMaterialInfos.isEmpty();
        } catch (Exception e) {
            log.error("查询FixtureMaterialInfo列表失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer addFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo) {
        try {
            return fixtureMaterialInfoMapper.addFixtureMaterialInfo(fixtureMaterialInfo);
        } catch (Exception e) {
            log.error("添加FixtureMaterialInfo失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @Override
    public Integer editFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo) {
        try {
            return fixtureMaterialInfoMapper.editFixtureMaterialInfo(fixtureMaterialInfo);
        } catch (Exception e) {
            log.error("修改FixtureMaterialInfo失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer removeFixtureMaterialInfo(FixtureMaterialInfo fixtureMaterialInfo) {
        try {
            return fixtureMaterialInfoMapper.removeFixtureMaterialInfo(fixtureMaterialInfo);
        } catch (Exception e) {
            log.error("删除FixtureMaterialInfo失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
