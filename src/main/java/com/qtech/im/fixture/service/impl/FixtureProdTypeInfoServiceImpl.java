package com.qtech.im.fixture.service.impl;

import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataScope;
import com.qtech.im.common.exception.TooManyResultsException;
import com.qtech.im.fixture.domain.FixtureMaterialCategoryProdType;
import com.qtech.im.fixture.domain.FixtureProdTypeInfo;
import com.qtech.im.fixture.mapper.FixtureMaterialCategoryProdTypeMapper;
import com.qtech.im.fixture.mapper.FixtureProdTypeInfoMapper;
import com.qtech.im.fixture.service.IFixtureProdTypeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/21 09:34:36
 * desc   :
 */

@Slf4j
@Service
public class FixtureProdTypeInfoServiceImpl implements IFixtureProdTypeInfoService {

    @Autowired
    FixtureProdTypeInfoMapper fixtureProdTypeInfoMapper;

    @Autowired
    FixtureMaterialCategoryProdTypeMapper fixtureMaterialCategoryProdTypeMapper;


    @DataScope(deptAlias = "ta")
    @Override
    public FixtureProdTypeInfo selectOneFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo) {
        List<FixtureProdTypeInfo> list = fixtureProdTypeInfoMapper.selectFixtureProdTypeInfoList(fixtureProdTypeInfo);

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
    public List<FixtureProdTypeInfo> selectFixtureProdTypeInfoList(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            return fixtureProdTypeInfoMapper.selectFixtureProdTypeInfoList(fixtureProdTypeInfo);
        } catch (Exception e) {
            log.error("selectFixtureProdTypeInfoList error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Boolean isFixtureProdTypeInfoExist(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            List<FixtureProdTypeInfo> fixtureProdTypeInfos = fixtureProdTypeInfoMapper.selectFixtureProdTypeInfoList(fixtureProdTypeInfo);
            return !fixtureProdTypeInfos.isEmpty();
        } catch (Exception e) {
            log.error("isFixtureProdTypeInfoExist error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer addFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            return fixtureProdTypeInfoMapper.addFixtureProdTypeInfo(fixtureProdTypeInfo);
        } catch (Exception e) {
            log.error("addFixtureProdTypeInfo error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer editFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            return fixtureProdTypeInfoMapper.editFixtureProdTypeInfo(fixtureProdTypeInfo);
        } catch (Exception e) {
            log.error("editFixtureProdTypeInfo error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Integer removeFixtureProdTypeInfo(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            return fixtureProdTypeInfoMapper.removeFixtureProdTypeInfo(fixtureProdTypeInfo);
        } catch (Exception e) {
            log.error("removeFixtureProdTypeInfo error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }

    @DataScope(deptAlias = "ta")
    @Override
    public Boolean isSecureDeletion(FixtureProdTypeInfo fixtureProdTypeInfo) {
        try {
            FixtureProdTypeInfo fixtureProdTypeInfoDb = selectOneFixtureProdTypeInfo(fixtureProdTypeInfo);
            if (Optional.ofNullable(fixtureProdTypeInfoDb.getpId()).isPresent()) {
                FixtureMaterialCategoryProdType fixtureMaterialCategoryProdType = new FixtureMaterialCategoryProdType();
                fixtureMaterialCategoryProdType.setPId(fixtureProdTypeInfoDb.getpId());
                List<FixtureMaterialCategoryProdType> fixtureMaterialCategoryProdTypes = fixtureMaterialCategoryProdTypeMapper.selectFixtureMaterialCategoryProdTypeList(fixtureMaterialCategoryProdType);

                return StringUtils.isEmpty(fixtureMaterialCategoryProdTypes);
            } else {
                return true;
            }
        } catch (Exception e) {
            log.error("isSecureDeletion error", e);
            throw new RuntimeException("系统处理发生异常，请联系系统负责人！");
        }
    }
}
