package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.ImAaListParamsStdModel;
import com.qtech.im.aa.mapper.ImAaListParamsStdModelMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.IImAaListParamsStdModelService;
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
public class ImAaListParamsStdModelServiceImpl extends ServiceImpl<ImAaListParamsStdModelMapper, ImAaListParamsStdModel> implements IImAaListParamsStdModelService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;

    @Override
    public List<ImAaListParamsStdModel> selectList(ImAaListParamsStdModel imAaListParamsStdModel) {
        LambdaQueryWrapper<ImAaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        if (imAaListParamsStdModel.getId() != null) {
            wrapper.eq(ImAaListParamsStdModel::getId, imAaListParamsStdModel.getId());
        }
        if (StringUtils.isNotBlank(imAaListParamsStdModel.getProdType())) {
            wrapper.eq(ImAaListParamsStdModel::getProdType, imAaListParamsStdModel.getProdType());
        }
        return list(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveOrUpdateAaListParamsStdModel(ImAaListParamsStdModel imAaListParamsStdModel) {
        // 检查数据是否存在
        LambdaQueryWrapper<ImAaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ImAaListParamsStdModel::getProdType, imAaListParamsStdModel.getProdType());
        ImAaListParamsStdModel one = getOne(wrapper);

        if (one != null) {
            // 数据已存在，执行更新操作
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + one.getProdType());
            imAaListParamsStdModel.setUpdateBy(getLoginUser().getUser().getNickName());
            imAaListParamsStdModel.setUpdateTime(DateUtils.getNowDate());
            boolean a = update(imAaListParamsStdModel, wrapper);
            boolean b = aaListParamsStdModelInfoService.saveOrUpdateAaListParamsStdModelInfo(imAaListParamsStdModel);
            if (!a || !b) {
                throw new RuntimeException("更新数据发生异常，请联系管理员！");
            }
            return true;
        }

        // 数据不存在，执行插入操作
        imAaListParamsStdModel.setCreateBy(getLoginUser().getUser().getNickName());
        imAaListParamsStdModel.setCreateTime(DateUtils.getNowDate());
        boolean a = save(imAaListParamsStdModel);
        boolean b = aaListParamsStdModelInfoService.saveOrUpdateAaListParamsStdModelInfo(imAaListParamsStdModel);
        if (!a || !b) {
            throw new RuntimeException("插入数据发生异常，请联系管理员！");
        }
        return true;
    }

    @Override
    public int batchInsert(List<ImAaListParamsStdModel> paramsModelList) {
        return 0;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateAaListParamsStdModel(ImAaListParamsStdModel aaListParamsStdModelDetail) {
        LambdaQueryWrapper<ImAaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ImAaListParamsStdModel::getProdType, aaListParamsStdModelDetail.getProdType());
        ImAaListParamsStdModel one = getOne(wrapper);

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
    public void deleteAaListParamsStdModel(ImAaListParamsStdModel aaListParamsStdModelDetail) {
        if (aaListParamsStdModelDetail != null) {
            try {
                String prodType = aaListParamsStdModelDetail.getProdType();
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                LambdaQueryWrapper<ImAaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ImAaListParamsStdModel::getProdType, prodType);
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
            ImAaListParamsStdModel res = getById(id);
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
    public Map<String, Object> uploadManual(List<ImAaListParamsStdModel> paramsModelList) {
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

        for (ImAaListParamsStdModel detail : paramsModelList) {
            try {
                int exists = selectList(detail).size();
                if (exists > 0) {
                    duplicateCount++;
                    continue;
                }

                ReflectionUtils.getAllDeclaredFields(ImAaListParamsStdModel.class).forEach(field -> {
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
    public Map<String, Object> uploadOnline(ImAaListParamsStdModel aaListParamsStdModelDetail) {
        List<ImAaListParamsStdModel> list = Collections.singletonList(aaListParamsStdModelDetail);
        return uploadManual(list);
    }

    @Override
    public ImAaListParamsStdModel selectOne(ImAaListParamsStdModel param) {
        if (param != null) {
            if (param.getId() != null) {
                return getById(param.getId());
            } else if (param.getProdType() != null) {
                LambdaQueryWrapper<ImAaListParamsStdModel> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ImAaListParamsStdModel::getProdType, param.getProdType());
                return getOne(wrapper);
            } else {
                throw new RuntimeException("参数错误，无法识别参数！");
            }
        }
        return null;
    }
}




