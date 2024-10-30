package com.qtech.im.fixture.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.domain.FixtureMaterialCategoryProdType;
import com.qtech.im.fixture.mapper.FixtureMaterialCategoryProdTypeMapper;
import com.qtech.im.fixture.service.IFixtureMaterialCategoryProdTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 11:04:31
 * desc   :
 */

@Slf4j
@Service
public class FixtureMaterialCategoryProdTypeServiceImpl implements IFixtureMaterialCategoryProdTypeService {

    @Autowired
    FixtureMaterialCategoryProdTypeMapper fixtureMaterialCategoryProdTypeMapper;

    @DataScope(deptAlias = "ta")
    @Override
    public FixtureMaterialCategoryProdType selectFixtureMaterialCategoryProdTypeOne(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        List<FixtureMaterialCategoryProdType> list = fixtureMaterialCategoryProdTypeMapper.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdType);
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
    public List<FixtureMaterialCategoryProdType> selectFixtureMaterialCategoryProdTypeList(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        try {
            return fixtureMaterialCategoryProdTypeMapper.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            log.error("查询FixtureMaterialCategoryProdType列表失败", e);
            throw new RuntimeException("数据库处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Boolean isFixtureMaterialCategoryProdTypeExist(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        try {
            List<FixtureMaterialCategoryProdType> fixtureMaterialCategoryProdTypes = fixtureMaterialCategoryProdTypeMapper.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdType);
            return !fixtureMaterialCategoryProdTypes.isEmpty();
        } catch (Exception e) {
            log.error("查询FixtureMaterialCategoryProdType列表失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer addFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        try {
            return fixtureMaterialCategoryProdTypeMapper.addFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            log.error("添加FixtureMaterialCategoryProdType失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer editFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        try {
            return fixtureMaterialCategoryProdTypeMapper.editFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            log.error("修改FixtureMaterialCategoryProdType失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer removeFixtureMaterialCategoryProdType(FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType) {
        try {
            return fixtureMaterialCategoryProdTypeMapper.removeFixtureMaterialCategoryProdType(fixtureMaterialCategoryProdType);
        } catch (Exception e) {
            log.error("删除FixtureMaterialCategoryProdType失败", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
