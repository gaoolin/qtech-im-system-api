package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
import com.qtech.im.aa.mapper.AaListParamsStdModelMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.IAaListParamsStdModelService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import com.qtech.im.aa.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;
import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * @author zhilin.gao
 * @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Service实现
 * @createDate 2024-10-29 14:30:19
 */
@DataSource(value = DataSourceType.FOURTH)
@Slf4j
@Service
public class AaListParamsStdModelServiceImpl extends ServiceImpl<AaListParamsStdModelMapper, AaListParamsStdModel> implements IAaListParamsStdModelService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;

    @Override
    public List<AaListParamsStdModel> selectList(AaListParamsStdModel aaListParamsStdModel) {
        LambdaQueryWrapper<AaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        if (aaListParamsStdModel.getId() != null) {
            wrapper.eq(AaListParamsStdModel::getId, aaListParamsStdModel.getId());
        }
        if (StringUtils.isNotBlank(aaListParamsStdModel.getProdType())) {
            wrapper.eq(AaListParamsStdModel::getProdType, aaListParamsStdModel.getProdType());
        }
        wrapper.orderByDesc(AaListParamsStdModel::getCreateTime);
        return list(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveOrUpdateAaListParamsStdModel(AaListParamsStdModel aaListParamsStdModel) {
        // 检查数据是否存在
        LambdaQueryWrapper<AaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AaListParamsStdModel::getProdType, aaListParamsStdModel.getProdType());
        AaListParamsStdModel one = getOne(wrapper);

        if (one != null) {
            // 数据已存在，执行更新操作
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + one.getProdType());
            aaListParamsStdModel.setUpdateBy(getLoginUser().getUser().getNickName());
            aaListParamsStdModel.setUpdateTime(DateUtils.getNowDate());
            boolean a = update(aaListParamsStdModel, wrapper);
            boolean b = aaListParamsStdModelInfoService.saveOrUpdateStdModelInfo(aaListParamsStdModel);
            if (!a || !b) {
                throw new RuntimeException("更新数据发生异常，请联系管理员！");
            }
            return true;
        }

        // 数据不存在，执行插入操作
        aaListParamsStdModel.setCreateBy(getLoginUser().getUser().getNickName());
        aaListParamsStdModel.setCreateTime(DateUtils.getNowDate());
        boolean a = save(aaListParamsStdModel);
        boolean b = aaListParamsStdModelInfoService.saveOrUpdateStdModelInfo(aaListParamsStdModel);
        if (!a || !b) {
            throw new RuntimeException("插入数据发生异常，请联系管理员！");
        }
        return true;
    }

    @Override
    public int batchInsert(List<AaListParamsStdModel> paramsModelList) {
        return 0;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateAaListParamsStdModel(AaListParamsStdModel aaListParamsStdModelDetail) {
        LambdaQueryWrapper<AaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AaListParamsStdModel::getProdType, aaListParamsStdModelDetail.getProdType());
        AaListParamsStdModel one = getOne(wrapper);

        boolean b = false;
        try {
            if (one == null) {
                throw new RuntimeException("修改数据发生异常，请联系管理员！");
            } else {
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + one.getProdType());
            }
            b = update(wrapper);
        } catch (Exception e) {
            log.error("修改数据发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("修改数据发生异常，请联系管理员！");
        }
        return b;
    }

    @Override
    public void deleteAaListParamsStdModel(AaListParamsStdModel aaListParamsStdModelDetail) {
        if (aaListParamsStdModelDetail != null) {
            try {
                String prodType = aaListParamsStdModelDetail.getProdType();
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                LambdaQueryWrapper<AaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(AaListParamsStdModel::getProdType, prodType);
                remove(wrapper);
            } catch (Exception e) {
                log.error("删除数据发生异常，请联系管理员！\n{}", e.getMessage());
                throw new RuntimeException("删除数据发生异常，请联系管理员！");
            }
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteAaListParamsStdModelByIds(List<Long> list) {
        list.forEach(id -> {
            AaListParamsStdModel res = getById(id);
            if (res != null) {
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + res.getProdType());
            }
        });

        boolean b = false;
        try {
            b = super.removeBatchByIds(list);
        } catch (Exception e) {
            log.error("删除数据发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("删除数据发生异常，请联系管理员！");
        }
        return b;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, Object> uploadManual(List<AaListParamsStdModel> paramsModelList) {
        Map<String, Object> result = new HashMap<>();
        if (paramsModelList == null || paramsModelList.isEmpty()) {
            result.put("flag", "0");
            result.put("msg", "Total: 0");
            return result;
        }

        int totalCount = paramsModelList.size();
        int successCount = 0;
        int failureCount = 0;
        int duplicateCount = 0;

        for (AaListParamsStdModel detail : paramsModelList) {
            try {
                int exists = selectList(detail).size();
                if (exists > 0) {
                    duplicateCount++;
                    continue;
                }

                ReflectionUtils.getAllDeclaredFields(AaListParamsStdModel.class).forEach(field -> {
                    field.setAccessible(true);
                    if (field.getType().equals(String.class)) {
                        try {
                            String value = (String) field.get(detail);
                            if (StringUtils.isBlank(value)) {
                                field.set(detail, null);
                            } else {
                                field.set(detail, StringUtils.trim(value));
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                boolean isInsert;
                AaListParamsStdModelInfo modelInfo = ModelDetailConvertToModelInfo.doConvert(detail);
                if (modelInfo != null) {
                    modelInfo.setCreateBy(getLoginUser().getUser().getNickName());
                    modelInfo.setCreateTime(DateUtils.getNowDate());
                    aaListParamsStdModelInfoService.save(modelInfo);
                    isInsert = save(detail);
                } else {
                    log.error("数据转换异常，请联系管理员！");
                    failureCount++;
                    continue;
                }

                if (isInsert) {
                    successCount++;
                } else {
                    failureCount++;
                }
            } catch (Exception e) {
                log.error("处理数据发生异常：{}", e.getMessage());
                failureCount++;
            }
        }

        boolean isAllSuccess = (successCount == totalCount);

        result.put("flag", isAllSuccess);
        StringBuilder msg = new StringBuilder("Total: " + totalCount);
        if (successCount > 0) {
            msg.append(", Success: ").append(successCount);
        }
        if (failureCount > 0) {
            msg.append(", Failure: ").append(failureCount);
        }
        if (duplicateCount > 0) {
            msg.append(", Duplicate: ").append(duplicateCount);
        }
        result.put("msg", msg.toString());

        return result;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, Object> uploadOnline(AaListParamsStdModel aaListParamsStdModelDetail) {
        List<AaListParamsStdModel> list = Collections.singletonList(aaListParamsStdModelDetail);
        return uploadManual(list);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AaListParamsStdModel selectOne(Long id) {
        LambdaQueryWrapper<AaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AaListParamsStdModel::getId, id);
        return getOne(wrapper);
    }
}




