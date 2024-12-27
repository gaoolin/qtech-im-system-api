package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.redis.RedisCache;
import com.qtech.im.aa.domain.AaListParamsStdTemplate;
import com.qtech.im.aa.domain.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.event.AaListParamsStdTemplateEvent;
import com.qtech.im.aa.mapper.AaListParamsStdTemplateMapper;
import com.qtech.im.aa.service.IAaListParamsStdTemplateInfoService;
import com.qtech.im.aa.service.IAaListParamsStdTemplateService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;

/**
 * @author zhilin.gao
 * @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Service实现
 * @createDate 2024-10-29 14:30:19
 */
@DataSource(value = DataSourceType.FOURTH)
@Slf4j
@Service
public class AaListParamsStdTemplateServiceImpl extends ServiceImpl<AaListParamsStdTemplateMapper, AaListParamsStdTemplate> implements IAaListParamsStdTemplateService {
    @Autowired
    private IAaListParamsStdTemplateInfoService aaListParamsStdModelInfoService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private LambdaQueryWrapper<AaListParamsStdTemplate> buildQueryWrapper(AaListParamsStdTemplate aaListParamsStdTemplate) {
        LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = new LambdaQueryWrapper<>();
        if (aaListParamsStdTemplate.getId() != null) {
            wrapper.eq(AaListParamsStdTemplate::getId, aaListParamsStdTemplate.getId());
        }
        if (StringUtils.isNotBlank(aaListParamsStdTemplate.getProdType())) {
            wrapper.eq(AaListParamsStdTemplate::getProdType, aaListParamsStdTemplate.getProdType());
        }
        return wrapper;
    }

    @Override
    public List<AaListParamsStdTemplate> selectList(AaListParamsStdTemplate aaListParamsStdTemplate) {
        LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = buildQueryWrapper(aaListParamsStdTemplate);
        wrapper.orderByDesc(AaListParamsStdTemplate::getCreateTime);
        return list(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveOrUpdateAaListParamsStdModel(AaListParamsStdTemplate aaListParamsStdTemplate) {
        // 检查数据是否存在
        LambdaUpdateWrapper<AaListParamsStdTemplate> updateWrapper = baseMapper.updateWithNullField(aaListParamsStdTemplate);
        LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = buildQueryWrapper(aaListParamsStdTemplate);
        AaListParamsStdTemplate one = getOne(wrapper);

        if (one != null) {
            // 数据已存在，执行更新操作
            aaListParamsStdTemplate.setUpdateBy(getLoginUser().getUser().getNickName());
            aaListParamsStdTemplate.setUpdateTime(DateUtils.getNowDate());
            boolean a = update(updateWrapper);
            boolean b = aaListParamsStdModelInfoService.saveOrUpdateStdModelInfo(aaListParamsStdTemplate);
            if (!a || !b) {
                throw new RuntimeException("更新数据发生异常，请联系管理员！");
            }

            // 发布更新事件
            applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, one.getProdType(), "UPDATE"));

            return true;
        }

        // 数据不存在，执行插入操作
        aaListParamsStdTemplate.setCreateBy(getLoginUser().getUser().getNickName());
        aaListParamsStdTemplate.setCreateTime(DateUtils.getNowDate());
        boolean a = save(aaListParamsStdTemplate);
        boolean b = aaListParamsStdModelInfoService.saveOrUpdateStdModelInfo(aaListParamsStdTemplate);
        if (!a || !b) {
            throw new RuntimeException("插入数据发生异常，请联系管理员！");
        }

        // 发布插入事件
        applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, aaListParamsStdTemplate.getProdType(), "INSERT"));

        return true;
    }

    @Override
    public int batchInsert(List<AaListParamsStdTemplate> paramsModelList) {
        return 0;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateAaListParamsStdModel(AaListParamsStdTemplate aaListParamsStdTemplate) {
        LambdaUpdateWrapper<AaListParamsStdTemplate> updateWrapper = baseMapper.updateWithNullField(aaListParamsStdTemplate);
        LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = buildQueryWrapper(aaListParamsStdTemplate);
        AaListParamsStdTemplate one = getOne(wrapper);

        boolean b = false;
        try {
            if (one == null) {
                throw new RuntimeException("修改数据发生异常，请联系管理员！");
            } else {
                aaListParamsStdTemplate.setUpdateBy(getLoginUser().getUser().getNickName());
                aaListParamsStdTemplate.setUpdateTime(DateUtils.getNowDate());
                b = update(updateWrapper);
                boolean c = aaListParamsStdModelInfoService.saveOrUpdateStdModelInfo(aaListParamsStdTemplate);
                if (!b || !c) {
                    throw new RuntimeException("更新数据发生异常，请联系管理员！");
                }

                // 发布更新事件
                applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, one.getProdType(), "UPDATE"));
            }
        } catch (Exception e) {
            log.error("修改数据发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("修改数据发生异常，请联系管理员！");
        }
        return b;
    }

    @Override
    public void deleteAaListParamsStdModel(AaListParamsStdTemplate aaListParamsStdTemplate) {
        if (aaListParamsStdTemplate != null) {
            try {
                String prodType = aaListParamsStdTemplate.getProdType();
                LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(AaListParamsStdTemplate::getProdType, prodType);
                remove(wrapper);

                // 发布删除事件
                applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, prodType, "DELETE"));
            } catch (Exception e) {
                log.error("删除数据发生异常，请联系管理员！\n{}", e.getMessage());
                throw new RuntimeException("删除数据发生异常，请联系管理员！", e);
            }
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteAaListParamsStdModelByIds(List<Long> list) {
        try {
            List<String> prodTypes = list.stream().map(this::getById).filter(Objects::nonNull).map(AaListParamsStdTemplate::getProdType).distinct().collect(Collectors.toList());

            // 发布删除事件
            prodTypes.forEach(prodType -> applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, prodType, "DELETE")));

            return super.removeBatchByIds(list);
        } catch (Exception e) {
            log.error("删除数据发生异常，请联系管理员！\n", e);
            throw new RuntimeException("删除数据发生异常，请联系管理员！", e);
        }
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, Object> uploadManual(List<AaListParamsStdTemplate> paramsModelList) {
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

        for (AaListParamsStdTemplate detail : paramsModelList) {
            try {
                if (!selectList(detail).isEmpty()) {
                    duplicateCount++;
                    continue;
                }

                trimAndNullifyStringFields(detail);

                AaListParamsStdTemplateInfo modelInfo = ModelDetailConvertToModelInfo.doConvert(detail);
                if (modelInfo != null) {
                    modelInfo.setCreateBy(getLoginUser().getUser().getNickName());
                    modelInfo.setCreateTime(DateUtils.getNowDate());
                    aaListParamsStdModelInfoService.save(modelInfo);
                    if (save(detail)) {
                        successCount++;
                    } else {
                        failureCount++;
                    }
                } else {
                    log.error("数据转换异常，请联系管理员！");
                    failureCount++;
                }
            } catch (Exception e) {
                log.error("处理数据发生异常：{}", e.getMessage(), e);
                failureCount++;
            }
            // 发布删除事件
            applicationEventPublisher.publishEvent(new AaListParamsStdTemplateEvent(this, detail.getProdType(), "INSERT"));
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
    public Map<String, Object> uploadOnline(AaListParamsStdTemplate aaListParamsStdTemplateDetail) {
        List<AaListParamsStdTemplate> list = Collections.singletonList(aaListParamsStdTemplateDetail);
        return uploadManual(list);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AaListParamsStdTemplate selectOne(Long id) {
        LambdaQueryWrapper<AaListParamsStdTemplate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AaListParamsStdTemplate::getId, id);
        return getOne(wrapper);
    }

    private void trimAndNullifyStringFields(AaListParamsStdTemplate detail) {
        Field[] fields = AaListParamsStdTemplate.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(detail);
                    field.set(detail, StringUtils.isBlank(value) ? null : StringUtils.trim(value));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
