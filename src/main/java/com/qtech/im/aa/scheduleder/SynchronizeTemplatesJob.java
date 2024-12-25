package com.qtech.im.aa.scheduleder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.IAaListParamsStdModelService;
import com.qtech.im.aa.utils.ModelDetailConvertToModelInfo;
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
    private IAaListParamsStdModelInfoService modelInfoService;
    @Autowired
    private IAaListParamsStdModelService modelService;
    @Autowired
    private RedisTemplate<String, AaListParamsStdModel> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplateInfo;
    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(cron = "0 */15 * * * ?")
    public void synchronizeTemplates() {
        // 获取数据库中模板明细和概要信息
        Map<String, AaListParamsStdModel> dbDetailMap = getDatabaseDetails();
        Map<String, AaListParamsStdModelInfo> dbSummaryMap = getDatabaseSummaries();

        // 比较并同步数据库模板明细和概要信息
        syncDatabaseDetailsAndSummaries(dbDetailMap, dbSummaryMap);

        // 比较并同步数据库概要信息与 Redis 数据
        Map<String, AaListParamsStdModelInfo> reDbSummaryMap = getDatabaseSummaries();
        syncDatabaseSummariesWithRedis(reDbSummaryMap);

        // 比较并同步数据库明细与 Redis 数据
        syncDatabaseDetailsWithRedis(dbDetailMap);

        // 获取 Redis 中模板明细和概要信息
        syncSummariesAndDetailsWithRedis(dbDetailMap);

        log.info(">>>>> Synchronization process completed.");
    }

    private void syncSummariesAndDetailsWithRedis(Map<String, AaListParamsStdModel> dbDetailMap) {
        List<Set<String>> sets = getRedisKeys();
        Set<String> modelKeys = sets.get(0);
        Set<String> modelInfoKeys = sets.get(1);

        // in detail
        HashSet<String> onlyInDetail = new HashSet<>(modelKeys);
        onlyInDetail.removeAll(modelInfoKeys);
        onlyInDetail.forEach(prodType -> {
            redisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
            log.info("Deleted from Redis: {}", prodType);
        });

        // in info
        HashSet<String> onlyInInfo = new HashSet<>(modelInfoKeys);
        onlyInInfo.removeAll(modelKeys);
        onlyInInfo.forEach(prodType -> {
            AaListParamsStdModel aaListParamsStdModel = dbDetailMap.get(prodType);
            if (aaListParamsStdModel != null) {
                redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, aaListParamsStdModel);
            }
        });

        // in both
    }

    private Map<String, AaListParamsStdModel> getDatabaseDetails() {
        return modelService.list().stream().filter(model -> model.getProdType() != null).collect(Collectors.toMap(AaListParamsStdModel::getProdType, model -> model));
    }

    private Map<String, AaListParamsStdModelInfo> getDatabaseSummaries() {
        return modelInfoService.list().stream().filter(model -> model.getProdType() != null).collect(Collectors.toMap(AaListParamsStdModelInfo::getProdType, model -> model));
    }

    private void syncDatabaseDetailsAndSummaries(Map<String, AaListParamsStdModel> dbDetailMap, Map<String, AaListParamsStdModelInfo> dbSummaryMap) {
        Set<String> detailKeys = dbDetailMap.keySet();
        Set<String> summaryKeys = dbSummaryMap.keySet();

        // Only in Details
        Set<String> onlyInDetails = new HashSet<>(detailKeys);
        onlyInDetails.removeAll(summaryKeys);
        onlyInDetails.forEach(prodType -> {
            AaListParamsStdModel detailModel = dbDetailMap.get(prodType);
            if (detailModel != null) {
                modelInfoService.saveOrUpdateStdModelInfo(detailModel);
                log.info(">>>>> Inserted into Summary Table: {}", prodType);
            }
        });

        // Only in Summary
        Set<String> onlyInSummary = new HashSet<>(summaryKeys);
        onlyInSummary.removeAll(detailKeys);
        onlyInSummary.forEach(prodType -> {
            modelInfoService.remove(new LambdaQueryWrapper<AaListParamsStdModelInfo>().eq(AaListParamsStdModelInfo::getProdType, prodType));
            log.info(">>>>> Deleted from Summary Table: {}", prodType);
        });

        // In Both
        Set<String> inBoth = new HashSet<>(detailKeys);
        inBoth.retainAll(summaryKeys);
        inBoth.forEach(prodType -> {
            AaListParamsStdModel detailModel = dbDetailMap.get(prodType);
            AaListParamsStdModelInfo summaryModel = dbSummaryMap.get(prodType);
            if (detailModel != null && summaryModel != null) {
                AaListParamsStdModelInfo updatedInfo = ModelDetailConvertToModelInfo.doConvert(detailModel);
                if (updatedInfo != null && !updatedInfo.equals(summaryModel)) {
                    updatedInfo.setUpdateBy("SYSTEM");
                    updatedInfo.setUpdateTime(new Date());
                    modelInfoService.saveOrUpdateStdModelInfo(updatedInfo);
                    log.info(">>>>> Updated in Summary Table: {}", prodType);
                }
            }
        });
    }

    private void syncDatabaseSummariesWithRedis(Map<String, AaListParamsStdModelInfo> dbSummaryMap) {
        // Compare and Sync
        dbSummaryMap.forEach((prodType, dbModel) -> {
            String redisKey = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
            try {
                String redisData = redisTemplateInfo.opsForValue().get(redisKey);
                AaListParamsStdModelInfo redisModel = redisData != null ? objectMapper.readValue(redisData, AaListParamsStdModelInfo.class) : null;

                if (!Objects.equals(dbModel, redisModel)) {
                    redisTemplateInfo.opsForValue().set(redisKey, objectMapper.writeValueAsString(dbModel));
                    log.info(">>>>> Updated Redis Template Info: {}", prodType);
                }
            } catch (Exception e) {
                log.error(">>>>> Error syncing Redis Template Info for {}", prodType, e);
            }
        });
    }

    private void syncDatabaseDetailsWithRedis(Map<String, AaListParamsStdModel> dbDetailMap) {
        dbDetailMap.forEach((prodType, dbModel) -> {
            String redisKey = REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType;
            AaListParamsStdModel redisModel = redisTemplate.opsForValue().get(redisKey);

            if (!Objects.equals(dbModel, redisModel)) {
                redisTemplate.opsForValue().set(redisKey, dbModel);
                log.info(">>>>> Updated Redis Template Detail: {}", prodType);
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
                        if (key.endsWith(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX)) {
                            redisModelInfoKeys.add(key);
                        } else {
                            redisModelKeys.add(key);
                        }
                    }
                }
                sets.add(redisModelKeys);
                sets.add(redisModelInfoKeys);
                return null;
            });
        } catch (Exception e) {
            log.error("Redis scan operation failed", e);
        }
        return sets;
    }
}