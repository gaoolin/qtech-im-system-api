package com.qtech.im.aa.scheduleder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdTemplate;
import com.qtech.im.aa.domain.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.service.IAaListParamsStdTemplateInfoService;
import com.qtech.im.aa.service.IAaListParamsStdTemplateService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
import com.qtech.share.aa.pojo.ImAaListParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX;
import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/24 16:38:12
 * desc   :
 */
@Slf4j
@Component
@EnableScheduling
@DataSource(DataSourceType.FOURTH)
public class SynchronizeTemplatesJob {

    @Autowired
    private IAaListParamsStdTemplateInfoService modelInfoService;
    @Autowired
    private IAaListParamsStdTemplateService modelService;
    @Autowired
    private RedisTemplate<String, ImAaListParams> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(cron = "0 */15 * * * ?")
    // @Scheduled(cron = "0 * * * * ?") // 每分钟检查一次数据一致性
    public void synchronizeTemplates() {
        // 获取数据库中模板明细和概要信息
        Map<String, AaListParamsStdTemplate> dbDetailMap = getDatabaseDetails();
        Map<String, AaListParamsStdTemplateInfo> dbSummaryMap = getDatabaseSummaries();

        // 比较并同步数据库模板明细和概要信息
        syncDatabaseDetailsAndSummaries(dbDetailMap, dbSummaryMap);

        // 比较并同步数据库概要信息与 Redis 数据
        Map<String, AaListParamsStdTemplateInfo> reDbSummaryMap = getDatabaseSummaries();
        syncDatabaseSummariesWithRedis(reDbSummaryMap);

        // 比较并同步数据库明细与 Redis 数据
        syncDatabaseDetailsWithRedis(dbDetailMap);

        // 获取 Redis 中模板明细和概要信息
        syncSummariesAndDetailsWithRedis(dbDetailMap);

        log.info(">>>>> Synchronization process completed.");
    }

    private void syncSummariesAndDetailsWithRedis(Map<String, AaListParamsStdTemplate> dbDetailMap) {
        List<Set<String>> sets = getRedisKeys();
        Set<String> modelKeys = sets.get(0);
        Set<String> modelInfoKeys = sets.get(1);

        // in detail
        HashSet<String> onlyInDetail = new HashSet<>(modelKeys);
        onlyInDetail.removeAll(modelInfoKeys);
        onlyInDetail.forEach(prodType -> {
            AaListParamsStdTemplate aaListParamsStdTemplate = dbDetailMap.get(prodType);
            if (aaListParamsStdTemplate != null) {
                AaListParamsStdTemplateInfo modelInfo = ModelDetailConvertToModelInfo.doConvert(aaListParamsStdTemplate);
                if (modelInfo != null) {
                    try {
                        stringRedisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType, objectMapper.writeValueAsString(modelInfo));
                    } catch (JsonProcessingException e) {
                        log.error(">>>>> JSON解析失败, msg: {}", aaListParamsStdTemplate, e);
                        throw new RuntimeException(e);
                    }
                    log.info(">>>>> Inserted into Detail Table: {}", prodType);
                }
            } else {
                deleteModelFromRedis(prodType, false);
            }
        });

        // in info
        HashSet<String> onlyInInfo = new HashSet<>(modelInfoKeys);
        onlyInInfo.removeAll(modelKeys);
        onlyInInfo.forEach(prodType -> {
            AaListParamsStdTemplate aaListParamsStdTemplate = dbDetailMap.get(prodType);
            if (aaListParamsStdTemplate != null) {
                redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, aaListParamsStdTemplate);
            } else {
                deleteModelFromRedis(prodType, true);
            }
        });

        // in both
    }

    private Map<String, AaListParamsStdTemplate> getDatabaseDetails() {
        return modelService.list().stream().filter(model -> model.getProdType() != null).collect(Collectors.toMap(AaListParamsStdTemplate::getProdType, model -> model));
    }

    private Map<String, AaListParamsStdTemplateInfo> getDatabaseSummaries() {
        return modelInfoService.list().stream().filter(model -> model.getProdType() != null).collect(Collectors.toMap(AaListParamsStdTemplateInfo::getProdType, model -> model));
    }

    private void syncDatabaseDetailsAndSummaries(Map<String, AaListParamsStdTemplate> dbDetailMap, Map<String, AaListParamsStdTemplateInfo> dbSummaryMap) {
        Set<String> detailKeys = dbDetailMap.keySet();
        Set<String> summaryKeys = dbSummaryMap.keySet();

        // Only in Details
        Set<String> onlyInDetails = new HashSet<>(detailKeys);
        onlyInDetails.removeAll(summaryKeys);
        onlyInDetails.forEach(prodType -> {
            AaListParamsStdTemplate detailModel = dbDetailMap.get(prodType);
            if (detailModel != null) {
                modelInfoService.saveOrUpdateStdModelInfo(detailModel);
                log.info(">>>>> Inserted into Summary Table: {}", prodType);
            }
        });

        // Only in Summary
        Set<String> onlyInSummary = new HashSet<>(summaryKeys);
        onlyInSummary.removeAll(detailKeys);
        onlyInSummary.forEach(prodType -> {
            modelInfoService.remove(new LambdaQueryWrapper<AaListParamsStdTemplateInfo>().eq(AaListParamsStdTemplateInfo::getProdType, prodType));
            log.info(">>>>> Deleted from Summary Table: {}", prodType);
        });

        // In Both
        Set<String> inBoth = new HashSet<>(detailKeys);
        inBoth.retainAll(summaryKeys);
        inBoth.forEach(prodType -> {
            AaListParamsStdTemplate detailModel = dbDetailMap.get(prodType);
            AaListParamsStdTemplateInfo summaryModel = dbSummaryMap.get(prodType);
            if (detailModel != null && summaryModel != null) {
                AaListParamsStdTemplateInfo updatedInfo = ModelDetailConvertToModelInfo.doConvert(detailModel);
                if (updatedInfo != null && !updatedInfo.equals(summaryModel)) {
                    updatedInfo.setUpdateBy("SYSTEM");
                    updatedInfo.setUpdateTime(new Date());
                    modelInfoService.saveOrUpdateStdModelInfo(updatedInfo);
                    log.info(">>>>> Updated in Summary Table: {}", prodType);
                }
            }
        });
    }

    private void syncDatabaseSummariesWithRedis(Map<String, AaListParamsStdTemplateInfo> dbSummaryMap) {
        // Compare and Sync
        dbSummaryMap.forEach((prodType, dbModel) -> {
            String redisKey = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
            try {
                String redisData = stringRedisTemplate.opsForValue().get(redisKey);
                AaListParamsStdTemplateInfo redisModel = redisData != null ? objectMapper.readValue(redisData, AaListParamsStdTemplateInfo.class) : null;

                if (!Objects.equals(dbModel, redisModel)) {
                    stringRedisTemplate.opsForValue().set(redisKey, objectMapper.writeValueAsString(dbModel));
                    log.info(">>>>> Updated Redis Template Info: {}", prodType);
                }
            } catch (Exception e) {
                log.error(">>>>> Error syncing Redis Template Info for {}", prodType, e);
            }
        });
    }

    private void syncDatabaseDetailsWithRedis(Map<String, AaListParamsStdTemplate> dbDetailMap) {
        dbDetailMap.forEach((prodType, dbModel) -> {
            String redisKey = REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType;
            ImAaListParams redisModel = redisTemplate.opsForValue().get(redisKey);

            if (!Objects.equals(dbModel, redisModel)) {
                redisTemplate.opsForValue().set(redisKey, dbModel);
                log.info(">>>>> Inserted Redis Template Detail: {}", prodType);
            }
        });
    }

    private List<Set<String>> getRedisKeys() {
        // 从 Redis 中获取所有模板
        Set<String> redisModelKeys = new CopyOnWriteArraySet<>();
        Set<String> redisModelInfoKeys = new CopyOnWriteArraySet<>();
        ArrayList<Set<String>> sets = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().match(REDIS_COMPARISON_MODEL_KEY_PREFIX + "*").build();

        try {
            redisTemplate.execute((RedisCallback<Void>) connection -> {
                try (Cursor<byte[]> cursor = connection.scan(options)) {
                    while (cursor.hasNext()) {
                        String key = new String(cursor.next());
                        if (key.startsWith(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX)) {
                            // 去掉前缀
                            String keyWithoutPrefix = key.substring(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX.length());
                            redisModelInfoKeys.add(keyWithoutPrefix);
                        } else {
                            String keyWithoutPrefix = key.substring(REDIS_COMPARISON_MODEL_KEY_PREFIX.length());
                            redisModelKeys.add(keyWithoutPrefix);
                        }
                    }
                }
                sets.add(redisModelKeys);
                sets.add(redisModelInfoKeys);
                return null;
            });
        } catch (Exception e) {
            log.error(">>>>> Redis scan operation failed", e);
        }
        return sets;
    }

    public void deleteModelFromRedis(String prodType, boolean isSummary) {
        String prefix = isSummary ? REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX : REDIS_COMPARISON_MODEL_KEY_PREFIX;

        try {
            String key = prefix + prodType;
            log.info(">>>>> Attempting to delete Redis key: {}", key);

            // Check if the key exists before attempting to delete
            Boolean exists = redisTemplate.hasKey(key);
            if (exists != null && exists) {
                Boolean delete = redisTemplate.delete(key);
                if (Boolean.TRUE.equals(delete)) {
                    log.info(">>>>> Successfully deleted Redis key: {}", key);
                } else {
                    log.warn(">>>>> Failed to delete Redis key: {}. Key may not exist.", key);
                }
            } else {
                log.warn(">>>>> Key does not exist in Redis: {}", key);
            }
        } catch (Exception e) {
            log.error(">>>>> Error deleting Redis key for {}: {}", prodType, e.getMessage());
        }
    }
}