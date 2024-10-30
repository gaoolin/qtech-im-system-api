package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;
import com.qtech.im.aa.domain.ImAaListParamsStdModelDetail;
import com.qtech.im.aa.mapper.AaListParamsStdModelInfoMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.ImAaListParamsStdModelDetailService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.qtech.im.aa.utils.Constants.REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX;
import static com.qtech.im.aa.utils.Constants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:01:06
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsStdModelInfoServiceImpl implements IAaListParamsStdModelInfoService {

    @Autowired
    private AaListParamsStdModelInfoMapper aaListParamsStdModelInfoMapper;

    @Autowired
    private ImAaListParamsStdModelDetailService aaListParamsStdModelDetailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<AaListParamsStdModelInfoVo> selectAaListParamsStdModelInfoList(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        try {
            return aaListParamsStdModelInfoMapper.selectAaListParamsStdModelInfoList(aaListParamsStdModelInfoVo);
        } catch (Exception e) {
            log.error("selectAaListParamsStdModelInfoList:" , e);
            throw new RuntimeException("查询数据库发生异常，请联系管理员。");
        }
    }

    @Override
    public AaListParamsStdModelInfoVo selectOneAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        List<AaListParamsStdModelInfoVo> list = aaListParamsStdModelInfoMapper.selectAaListParamsStdModelInfoList(aaListParamsStdModelInfoVo);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                throw new TooManyResultsException(String.format("Expected one result (or null) to be returned by selectOne(), but found: %s" , size));
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insertAaListParamsStdModelInfo(Object entity) {
        try {
            int result = 0;
            if (entity.getClass().equals(AaListParamsStdModelInfoVo.class)) {
                AaListParamsStdModelInfoVo modelInfo = (AaListParamsStdModelInfoVo) entity;
                result = aaListParamsStdModelInfoMapper.insertAaListParamsStdModelInfo(modelInfo);
            } else if (entity.getClass().equals(ImAaListParamsStdModelDetail.class)) {
                ImAaListParamsStdModelDetail modelDetail = (ImAaListParamsStdModelDetail) entity;
                AaListParamsStdModelInfoVo param = ModelDetailConvertToModelInfo.doConvert(modelDetail);
                if (param != null) {
                    param.setCreateBy(modelDetail.getCreateBy());
                    param.setCreateTime(modelDetail.getCreateTime());
                    try {
                        result = aaListParamsStdModelInfoMapper.insertAaListParamsStdModelInfo(param);
                    } catch (Exception e) {
                        log.error("insertAaListParamsStdModelInfoByUpload:" , e);
                        throw new RuntimeException("保存到数据库发生异常，请联系管理员！");
                    }
                } else {
                    throw new RuntimeException("保存到数据库发生异常，请联系管理员！");
                }
            } else {
                throw new IllegalArgumentException("Unsupported entity type: " + entity.getClass().getName());
            }
            return result;
        } catch (Exception e) {
            log.error("insertAaListParamsStdModelInfo:" , e);
            throw new RuntimeException("保存到数据库发生异常，请联系管理员！");
        }
    }

    @Override
    public int updateAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {


        try {
            String prodType = aaListParamsStdModelInfoVo.getProdType();
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
            return aaListParamsStdModelInfoMapper.updateAaListParamsStdModelInfo(aaListParamsStdModelInfoVo);
        } catch (Exception e) {
            log.error("updateAaListParamsStdModelInfo:" , e);
            throw new RuntimeException("修改数据发生异常，请联系管理员。");
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public int deleteAaListParamsStdModelInfoByIds(Long[] list) {
        try {
            for (Long l : list) {
                AaListParamsStdModelInfoVo param = new AaListParamsStdModelInfoVo();
                param.setId(l);
                AaListParamsStdModelInfoVo result = selectOneAaListParamsStdModelInfo(param);
                if (result != null) {
                    String prodType = result.getProdType();
                    ImAaListParamsStdModelDetail paramDetail = new ImAaListParamsStdModelDetail();
                    paramDetail.setProdType(prodType);
                    aaListParamsStdModelDetailService.deleteAaListParamsStdModel(paramDetail);
                    stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                    stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                }
            }
            return aaListParamsStdModelInfoMapper.deleteAaListParamsStdModelInfoByIds(list);
        } catch (Exception e) {
            log.error("deleteAaListParamsStdModelInfoByIds:" , e);
            throw new RuntimeException("删除数据发生异常，请联系管理员。");
        }
    }
}
