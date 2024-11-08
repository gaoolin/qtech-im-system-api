package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.ImAaListParamsStdModelDetail;
import com.qtech.im.aa.mapper.ImAaListParamsStdModelDetailMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.ImAaListParamsStdModelDetailService;
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
import static com.qtech.im.aa.utils.Constants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * @author zhilin.gao
 * @description 针对表【IM_AA_LIST_PARAMS_STD_MODEL】的数据库操作Service实现
 * @createDate 2024-10-29 14:30:19
 */
@DataSource(value = DataSourceType.FOURTH)
@Slf4j
@Service
public class ImAaListParamsStdModelDetailServiceImpl extends ServiceImpl<ImAaListParamsStdModelDetailMapper, ImAaListParamsStdModelDetail> implements ImAaListParamsStdModelDetailService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;

    @Override
    public List<ImAaListParamsStdModelDetail> selectList(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail) {
        LambdaQueryWrapper<ImAaListParamsStdModelDetail> wrapper = new LambdaQueryWrapper<>();
        if (imAaListParamsStdModelDetail.getId() != null) {
            wrapper.eq(ImAaListParamsStdModelDetail::getId, imAaListParamsStdModelDetail.getId());
        }
        if (StringUtils.isNotBlank(imAaListParamsStdModelDetail.getProdType())) {
            wrapper.eq(ImAaListParamsStdModelDetail::getProdType, imAaListParamsStdModelDetail.getProdType());
        }
        return list(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveOrUpdateAaListParamsStdModel(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail) {
        // 检查数据是否存在
        LambdaQueryWrapper<ImAaListParamsStdModelDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ImAaListParamsStdModelDetail::getProdType, imAaListParamsStdModelDetail.getProdType());
        ImAaListParamsStdModelDetail one = getOne(wrapper);

        if (one != null) {
            // 数据已存在，执行更新操作
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + one.getProdType());
            imAaListParamsStdModelDetail.setUpdateBy(getLoginUser().getUser().getNickName());
            imAaListParamsStdModelDetail.setUpdateTime(DateUtils.getNowDate());
            return update(imAaListParamsStdModelDetail, wrapper);
        }

        imAaListParamsStdModelDetail.setCreateBy(getLoginUser().getUser().getNickName());
        imAaListParamsStdModelDetail.setCreateTime(DateUtils.getNowDate());
        return save(imAaListParamsStdModelDetail);
    }

    @Override
    public int batchInsert(List<ImAaListParamsStdModelDetail> paramsModelList) {
        return 0;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        LambdaQueryWrapper<ImAaListParamsStdModelDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ImAaListParamsStdModelDetail::getProdType, aaListParamsStdModelDetail.getProdType());
        ImAaListParamsStdModelDetail one = getOne(wrapper);

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
    public void deleteAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        if (aaListParamsStdModelDetail != null) {
            try {
                String prodType = aaListParamsStdModelDetail.getProdType();
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                LambdaQueryWrapper<ImAaListParamsStdModelDetail> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ImAaListParamsStdModelDetail::getProdType, prodType);
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
            ImAaListParamsStdModelDetail res = getById(id);
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
    public Map<String, Object> uploadManual(List<ImAaListParamsStdModelDetail> paramsModelList) {
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

        for (ImAaListParamsStdModelDetail detail : paramsModelList) {
            try {
                int exists = selectList(detail).size();
                if (exists > 0) {
                    duplicateCount++;
                    continue;
                }

                ReflectionUtils.getAllDeclaredFields(ImAaListParamsStdModelDetail.class).forEach(field -> {
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

                boolean insertCount = save(detail);
                aaListParamsStdModelInfoService.insertAaListParamsStdModelInfo(detail);
                if (insertCount) {
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
    public Map<String, Object> uploadOnline(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        List<ImAaListParamsStdModelDetail> list = Collections.singletonList(aaListParamsStdModelDetail);
        return uploadManual(list);
    }

    @Override
    public ImAaListParamsStdModelDetail selectOne(ImAaListParamsStdModelDetail param) {
        if (param != null) {
            if (param.getId() != null) {
                return getById(param.getId());
            } else if (param.getProdType() != null) {
                LambdaQueryWrapper<ImAaListParamsStdModelDetail> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ImAaListParamsStdModelDetail::getProdType, param.getProdType());
                return getOne(wrapper);
            } else {
                throw new RuntimeException("参数错误，无法识别参数！");
            }
        }
        return null;
    }
}




