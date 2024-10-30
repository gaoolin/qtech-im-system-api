package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.ImAaListParamsStdModelDetail;
import com.qtech.im.aa.mapper.ImAaListParamsStdModelDetailMapper;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.ImAaListParamsStdModelDetailService;
import com.qtech.im.aa.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
@DataSource(value = DataSourceType.SECOND)
@Slf4j
@Service
public class ImAaListParamsStdModelDetailServiceImpl extends ServiceImpl<ImAaListParamsStdModelDetailMapper, ImAaListParamsStdModelDetail>
        implements ImAaListParamsStdModelDetailService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;

    @Override
    public List<ImAaListParamsStdModelDetail> selectAaListParamsStdModelList(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail) {
        return baseMapper.selectList(Wrappers.<ImAaListParamsStdModelDetail>lambdaQuery(imAaListParamsStdModelDetail));
    }

    @Override
    public ImAaListParamsStdModelDetail selectOne(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail) {
        QueryWrapper<ImAaListParamsStdModelDetail> wrapper = new QueryWrapper<>(imAaListParamsStdModelDetail);
        Map<String, Object> params = imAaListParamsStdModelDetail.getParams();

        if (params != null) {
            wrapper.between("create_time" , params.get("beginTime"), params.get("endTime"));
        }
        return super.getOne(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public boolean insertAaListParamsStdModel(ImAaListParamsStdModelDetail imAaListParamsStdModelDetail) {
        // 检查数据是否存在
        ImAaListParamsStdModelDetail existingModelDetail = baseMapper.selectOne(Wrappers.<ImAaListParamsStdModelDetail>lambdaQuery(imAaListParamsStdModelDetail));

        boolean rowsAffected = false;
        if (existingModelDetail == null) {
            // 数据不存在，执行插入操作
            try {
                rowsAffected = super.save(imAaListParamsStdModelDetail);
                aaListParamsStdModelInfoService.insertAaListParamsStdModelInfo(imAaListParamsStdModelDetail);
            } catch (Exception e) {
                log.error("存储数据发生异常，请联系管理员！\n{}" , e.getMessage());
                throw new RuntimeException("存储数据发生异常，请联系管理员！");
            }
        } else {
            // 数据存在，执行更新操作
            // 更新前先设置更新人和更新时间
            imAaListParamsStdModelDetail.setId(existingModelDetail.getId()); // 确保ID正确
            String nickName = getLoginUser().getUser().getNickName();
            imAaListParamsStdModelDetail.setUpdateBy(nickName);

            try {
                rowsAffected = updateAaListParamsStdModel(imAaListParamsStdModelDetail);
            } catch (Exception e) {
                log.error("修改数据发生异常，请联系管理员！\n{}" , e.getMessage());
                throw new RuntimeException("修改数据发生异常，请联系管理员！");
            }
        }
        return rowsAffected;
    }

    @Override
    public int batchInsert(List<ImAaListParamsStdModelDetail> paramsModelList) {
        return 0;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        List<ImAaListParamsStdModelDetail> list = super.list(Wrappers.<ImAaListParamsStdModelDetail>lambdaQuery(aaListParamsStdModelDetail));
        if (CollectionUtils.isNotEmpty(list)) {
            stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + list.get(0).getProdType());
        }

        boolean b = false;
        try {
            b = super.update(Wrappers.<ImAaListParamsStdModelDetail>lambdaQuery(aaListParamsStdModelDetail));
        } catch (Exception e) {
            log.error("修改数据发生异常，请联系管理员！\n{}" , e.getMessage());
            throw new RuntimeException("修改数据发生异常，请联系管理员！");
        }
        return b;
    }

    @Override
    public boolean deleteAaListParamsStdModel(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        if (aaListParamsStdModelDetail != null) {
            try {
                String prodType = aaListParamsStdModelDetail.getProdType();
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                return super.remove(Wrappers.<ImAaListParamsStdModelDetail>lambdaQuery(aaListParamsStdModelDetail));
            } catch (Exception e) {
                log.error("删除数据发生异常，请联系管理员！\n{}" , e.getMessage());
                throw new RuntimeException("删除数据发生异常，请联系管理员！");
            }
        }
        return false;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean deleteAaListParamsStdModelByIds(List<Long> list) {

        list.forEach(id -> {
            ImAaListParamsStdModelDetail aaListParamsStdModelDetailParams = new ImAaListParamsStdModelDetail();
            aaListParamsStdModelDetailParams.setId(id);
            ImAaListParamsStdModelDetail res = selectOne(aaListParamsStdModelDetailParams);
            if (res != null) {
                stringRedisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + res.getProdType());
            }
        });

        boolean b = false;
        try {
            b = super.removeBatchByIds(list);
        } catch (Exception e) {
            log.error("删除数据发生异常，请联系管理员！\n{}" , e.getMessage());
            throw new RuntimeException("删除数据发生异常，请联系管理员！");
        }
        return b;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, Object> uploadManual(List<ImAaListParamsStdModelDetail> paramsModelList) {
        Map<String, Object> result = new HashMap<>();
        if (paramsModelList == null || paramsModelList.isEmpty()) {
            result.put("flag" , "0");
            result.put("msg" , "Total: 0");
            return result;
        }

        int totalCount = paramsModelList.size();
        int successCount = 0;
        int failureCount = 0;
        int duplicateCount = 0;

        for (ImAaListParamsStdModelDetail detail : paramsModelList) {
            try {
                int exists = baseMapper.checkIfExists(detail);
                if (exists > 0) {
                    duplicateCount++;
                    continue;
                }

                ReflectionUtils.getAllDeclaredFields(ImAaListParamsStdModelDetail.class)
                        .forEach(field -> {
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

                int insertCount = baseMapper.insert(detail);
                aaListParamsStdModelInfoService.insertAaListParamsStdModelInfo(detail);
                if (insertCount > 0) {
                    successCount++;
                } else {
                    failureCount++;
                }
            } catch (Exception e) {
                log.error("处理数据发生异常：{}" , e.getMessage());
                failureCount++;
            }
        }

        boolean isAllSuccess = (successCount == totalCount);

        result.put("flag" , isAllSuccess);
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
        result.put("msg" , msg.toString());

        return result;
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Map<String, Object> uploadOnline(ImAaListParamsStdModelDetail aaListParamsStdModelDetail) {
        List<ImAaListParamsStdModelDetail> list = Collections.singletonList(aaListParamsStdModelDetail);
        return uploadManual(list);
    }

    @Override
    public boolean save(ImAaListParamsStdModelDetail entity) {
        return super.save(entity);
    }

}




