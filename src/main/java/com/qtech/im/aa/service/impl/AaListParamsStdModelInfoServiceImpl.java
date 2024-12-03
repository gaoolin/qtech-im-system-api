package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;
import com.qtech.im.aa.mapper.AaListParamsStdModelInfoMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:01:06
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.FOURTH)
@Service
public class AaListParamsStdModelInfoServiceImpl extends ServiceImpl<AaListParamsStdModelInfoMapper, AaListParamsStdModelInfoVo> implements IAaListParamsStdModelInfoService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static LambdaQueryWrapper<AaListParamsStdModelInfoVo> buildQueryWrapper(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        LambdaQueryWrapper<AaListParamsStdModelInfoVo> wrapper = new LambdaQueryWrapper<>();
        if (aaListParamsStdModelInfoVo.getId() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getId, aaListParamsStdModelInfoVo.getId());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getProdType())) {
            wrapper.eq(AaListParamsStdModelInfoVo::getProdType, aaListParamsStdModelInfoVo.getProdType());
        }
        if (aaListParamsStdModelInfoVo.getListParams() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getListParams, aaListParamsStdModelInfoVo.getListParams());
        }
        if (aaListParamsStdModelInfoVo.getItemParams() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getItemParams, aaListParamsStdModelInfoVo.getItemParams());
        }
        if (aaListParamsStdModelInfoVo.getStatus() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getStatus, aaListParamsStdModelInfoVo.getStatus());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getProvider())) {
            wrapper.eq(AaListParamsStdModelInfoVo::getProvider, aaListParamsStdModelInfoVo.getProvider());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getBelongTo())) {
            wrapper.eq(AaListParamsStdModelInfoVo::getBelongTo, aaListParamsStdModelInfoVo.getBelongTo());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getSearchValue())) {
            wrapper.like(AaListParamsStdModelInfoVo::getSearchValue, aaListParamsStdModelInfoVo.getSearchValue());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getCreateBy())) {
            wrapper.eq(AaListParamsStdModelInfoVo::getCreateBy, aaListParamsStdModelInfoVo.getCreateBy());
        }
        if (aaListParamsStdModelInfoVo.getCreateTime() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getCreateTime, aaListParamsStdModelInfoVo.getCreateTime());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfoVo.getUpdateBy())) {
            wrapper.eq(AaListParamsStdModelInfoVo::getUpdateBy, aaListParamsStdModelInfoVo.getUpdateBy());
        }
        if (aaListParamsStdModelInfoVo.getUpdateTime() != null) {
            wrapper.eq(AaListParamsStdModelInfoVo::getUpdateTime, aaListParamsStdModelInfoVo.getUpdateTime());
        }

        return wrapper;
    }

    @Override
    public List<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        try {
            LambdaQueryWrapper<AaListParamsStdModelInfoVo> wrapper = buildQueryWrapper(aaListParamsStdModelInfoVo);
            wrapper.orderByDesc(AaListParamsStdModelInfoVo::getCreateTime);
            return list(wrapper);
        } catch (Exception e) {
            log.error("selectAaListParamsStdModelInfoList:", e);
            throw new RuntimeException("查询数据库发生异常，请联系管理员。");
        }
    }

    @Override
    public boolean updateStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo) {
        try {
            String prodType = aaListParamsStdModelInfoVo.getProdType();
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);

            LambdaQueryWrapper<AaListParamsStdModelInfoVo> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isBlank(prodType) && aaListParamsStdModelInfoVo.getId() == null) {
                throw new RuntimeException("产品类型不能为空！");
            } else {
                if (StringUtils.isNotBlank(prodType)) {
                    queryWrapper.eq(AaListParamsStdModelInfoVo::getProdType, prodType);
                }
                if (aaListParamsStdModelInfoVo.getId() != null) {
                    queryWrapper.eq(AaListParamsStdModelInfoVo::getId, aaListParamsStdModelInfoVo.getId());
                }
            }
            return update(aaListParamsStdModelInfoVo, queryWrapper);
        } catch (Exception e) {
            log.error("updateStdModelInfo:", e);
            throw new RuntimeException("修改数据发生异常，请联系管理员。");
        }
    }

    @Override
    @Transactional
    public boolean saveOrUpdateStdModelInfo(Object entity) {
        AaListParamsStdModelInfoVo modelInfo = null;
        AaListParamsStdModel modelDetail = null;

        try {
            if (entity instanceof AaListParamsStdModelInfoVo) {
                modelInfo = (AaListParamsStdModelInfoVo) entity;
            } else if (entity instanceof AaListParamsStdModel) {
                modelDetail = (AaListParamsStdModel) entity;
                modelInfo = ModelDetailConvertToModelInfo.doConvert(modelDetail);
            }

            if (modelInfo != null) {

                assert modelDetail != null;
                if (StringUtils.isNotEmpty(modelDetail.getCreateBy())) {
                    modelInfo.setCreateBy(modelDetail.getCreateBy());
                }

                if (modelDetail.getCreateTime() != null) {
                    modelInfo.setCreateTime(modelDetail.getCreateTime());
                }

                if (StringUtils.isNotBlank(modelDetail.getUpdateBy())) {
                    modelInfo.setUpdateBy(modelDetail.getUpdateBy());
                }

                if (modelDetail.getUpdateTime() != null) {
                    modelInfo.setUpdateTime(modelDetail.getUpdateTime());
                }
                return saveOrUpdateModelInfo(modelInfo);
            }
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            log.error("saveOrUpdateAaListParamsStdModelInfo:", e);
        }
        return false;
    }

    @Transactional
    public boolean saveOrUpdateModelInfo(AaListParamsStdModelInfoVo modelInfo) {
        try {
            LambdaQueryWrapper<AaListParamsStdModelInfoVo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AaListParamsStdModelInfoVo::getProdType, modelInfo.getProdType());
            if (getOne(wrapper) != null) {
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + modelInfo.getProdType());
                return update(modelInfo, wrapper);
            } else {
                return save(modelInfo);
            }
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            log.error("saveOrUpdateModelInfo:", e);
        }
        return false;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteStdModelInfoByIds(Long[] list) {
        try {
            return removeBatchByIds(Arrays.asList(list));
        } catch (Exception e) {
            log.error("deleteStdModelInfoByIds:", e);
            throw new RuntimeException("删除数据发生异常，请联系管理员。");
        }
    }
}
