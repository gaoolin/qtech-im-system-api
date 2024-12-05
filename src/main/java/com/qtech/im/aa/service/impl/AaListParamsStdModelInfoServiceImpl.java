package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
import com.qtech.im.aa.mapper.AaListParamsStdModelInfoMapper;
import com.qtech.im.aa.mapper.AaListParamsStdModelMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import com.qtech.im.aa.vo.AaListParamsStdModelInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class AaListParamsStdModelInfoServiceImpl extends ServiceImpl<AaListParamsStdModelInfoMapper, AaListParamsStdModelInfo> implements IAaListParamsStdModelInfoService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AaListParamsStdModelMapper aaListParamsStdModelMapper;

    public static LambdaQueryWrapper<AaListParamsStdModelInfo> buildQueryWrapper(AaListParamsStdModelInfo aaListParamsStdModelInfo) {
        LambdaQueryWrapper<AaListParamsStdModelInfo> wrapper = new LambdaQueryWrapper<>();
        if (aaListParamsStdModelInfo.getId() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getId, aaListParamsStdModelInfo.getId());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfo.getProdType())) {
            wrapper.eq(AaListParamsStdModelInfo::getProdType, aaListParamsStdModelInfo.getProdType());
        }
        if (aaListParamsStdModelInfo.getListParams() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getListParams, aaListParamsStdModelInfo.getListParams());
        }
        if (aaListParamsStdModelInfo.getItemParams() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getItemParams, aaListParamsStdModelInfo.getItemParams());
        }
        if (aaListParamsStdModelInfo.getStatus() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getStatus, aaListParamsStdModelInfo.getStatus());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfo.getProvider())) {
            wrapper.eq(AaListParamsStdModelInfo::getProvider, aaListParamsStdModelInfo.getProvider());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfo.getBelongTo())) {
            wrapper.eq(AaListParamsStdModelInfo::getBelongTo, aaListParamsStdModelInfo.getBelongTo());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfo.getCreateBy())) {
            wrapper.eq(AaListParamsStdModelInfo::getCreateBy, aaListParamsStdModelInfo.getCreateBy());
        }
        if (aaListParamsStdModelInfo.getCreateTime() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getCreateTime, aaListParamsStdModelInfo.getCreateTime());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModelInfo.getUpdateBy())) {
            wrapper.eq(AaListParamsStdModelInfo::getUpdateBy, aaListParamsStdModelInfo.getUpdateBy());
        }
        if (aaListParamsStdModelInfo.getUpdateTime() != null) {
            wrapper.eq(AaListParamsStdModelInfo::getUpdateTime, aaListParamsStdModelInfo.getUpdateTime());
        }

        return wrapper;
    }

    @Override
    public List<AaListParamsStdModelInfoVo> selectStdModelInfoList(AaListParamsStdModelInfo aaListParamsStdModelInfo) {
        try {
            ArrayList<AaListParamsStdModelInfoVo> vos = new ArrayList<>();
            LambdaQueryWrapper<AaListParamsStdModelInfo> wrapper = buildQueryWrapper(aaListParamsStdModelInfo);
            wrapper.orderByDesc(AaListParamsStdModelInfo::getCreateTime);
            list(wrapper).forEach(modelInfo -> {
                vos.add(new AaListParamsStdModelInfoVo(modelInfo));
            });
            return vos;
        } catch (Exception e) {
            log.error("selectStdModelInfoList:", e);
            throw new RuntimeException("查询数据库发生异常，请联系管理员。");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean updateStdModelInfo(AaListParamsStdModelInfo aaListParamsStdModelInfo) {
        try {
            String prodType = aaListParamsStdModelInfo.getProdType();
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);

            LambdaQueryWrapper<AaListParamsStdModelInfo> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isBlank(prodType) && aaListParamsStdModelInfo.getId() == null) {
                throw new RuntimeException("产品类型不能为空！");
            } else {
                if (StringUtils.isNotBlank(prodType)) {
                    queryWrapper.eq(AaListParamsStdModelInfo::getProdType, prodType);
                }
                if (aaListParamsStdModelInfo.getId() != null) {
                    queryWrapper.eq(AaListParamsStdModelInfo::getId, aaListParamsStdModelInfo.getId());
                }
            }
            return update(aaListParamsStdModelInfo, queryWrapper);
        } catch (Exception e) {
            log.error("updateStdModelInfo:", e);
            throw new RuntimeException("修改数据发生异常，请联系管理员。");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean saveOrUpdateStdModelInfo(Object entity) {
        AaListParamsStdModelInfo modelInfo = null;
        AaListParamsStdModel modelDetail = null;

        try {
            if (entity instanceof AaListParamsStdModelInfo) {
                modelInfo = (AaListParamsStdModelInfo) entity;
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
                return doSaveOrUpdate(modelInfo);
            }
        } catch (Exception e) {
            // 记录日志或进行其他异常处理
            log.error("saveOrUpdateAaListParamsStdModelInfo:", e);
        }
        return false;
    }


    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public boolean doSaveOrUpdate(AaListParamsStdModelInfo modelInfo) {
        try {
            LambdaQueryWrapper<AaListParamsStdModelInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AaListParamsStdModelInfo::getProdType, modelInfo.getProdType());
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

    /**
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteStdModelInfoById(Long id) {
        try {
            AaListParamsStdModelInfo modelInfo = getById(id);
            if (modelInfo == null) {
                return false; // 或者抛出异常
            }
            String prodType = modelInfo.getProdType();
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
            boolean a = removeById(id);
            int result = aaListParamsStdModelMapper.deleteByProdType(prodType);
            boolean b = result > 0;
            return a && b;
        } catch (Exception e) {
            log.error("deleteStdModelInfoById:", e);
            throw new RuntimeException("删除标准模型信息失败，请检查日志");
        }
    }


}
