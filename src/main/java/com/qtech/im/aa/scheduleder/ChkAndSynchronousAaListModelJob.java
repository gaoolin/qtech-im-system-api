package com.qtech.im.aa.scheduleder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsStdModel;
import com.qtech.im.aa.domain.AaListParamsStdModelInfo;
import com.qtech.im.aa.service.IAaListParamsStdModelInfoService;
import com.qtech.im.aa.service.IAaListParamsStdModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX;
import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/23 16:51:09
 * desc   :
 */
@Slf4j
// @Component
// @EnableScheduling
@DataSource(DataSourceType.FOURTH)
public class ChkAndSynchronousAaListModelJob {
    private final IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;
    private final IAaListParamsStdModelService aaListParamsStdModelService;
    private final RedisTemplate<String, AaListParamsStdModel> redisTemplate;
    private final StringRedisTemplate redisTemplateInfo;
    private final ObjectMapper objectMapper;

    // @Autowired
    public ChkAndSynchronousAaListModelJob(ObjectMapper objectMapper, RedisTemplate<String, AaListParamsStdModel> redisTemplate, StringRedisTemplate redisTemplateInfo, IAaListParamsStdModelService aaListParamsStdModelService, IAaListParamsStdModelInfoService aaListParamsStdModelInfoService) {
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        this.redisTemplateInfo = redisTemplateInfo;
        this.aaListParamsStdModelService = aaListParamsStdModelService;
        this.aaListParamsStdModelInfoService = aaListParamsStdModelInfoService;
    }

    // @Scheduled(cron = "0 * * * * ?") // 每分钟检查一次数据一致性
    public void chkAndSynchronousAaListModel() {
        try {
            // 从数据库中获取所有标准模板
            List<AaListParamsStdModel> dbModels = aaListParamsStdModelService.list();
            List<AaListParamsStdModelInfo> dbModelInfos = aaListParamsStdModelInfoService.list();
            Map<String, AaListParamsStdModel> dbModelMap = dbModels.stream()
                    .collect(Collectors.toMap(AaListParamsStdModel::getProdType, model -> model));

            // 模版信息
            Set<String> keysToKeep = new HashSet<>();


            // 从 Redis 中获取所有模板
            Set<String> redisModelKeys = new HashSet<>();
            Set<String> redisModelInfoKeys = new HashSet<>();
            ScanOptions options = ScanOptions.scanOptions().match(REDIS_COMPARISON_MODEL_KEY_PREFIX + "*").build();
            try (Cursor<byte[]> cursor = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().scan(options)) {
                while (cursor.hasNext()) {
                    String key = new String(cursor.next());
                    if (key.startsWith(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX)) {
                        redisModelInfoKeys.add(key);
                    } else {
                        redisModelKeys.add(key);
                    }
                }
            }

            Map<String, AaListParamsStdModel> redisModels = new HashMap<>();
            if (!redisModelKeys.isEmpty()) {
                List<AaListParamsStdModel> list = redisTemplate.opsForValue().multiGet(redisModelKeys);
                if (list != null) {
                    for (AaListParamsStdModel template : list) {
                        if (template != null) {
                            redisModels.put(template.getProdType(), template);
                        }
                    }
                }
            }

            // 数据库中的模板 机型 集合
            Set<String> dbTemplateProdTypes = dbModelMap.keySet();

            // Redis 中的模板 机型 集合
            Set<String> redisTemplateProdTypes = redisModels.keySet();

            // 数据库中有而 Redis 中没有的模板
            Set<String> onlyInDb = new HashSet<>(dbTemplateProdTypes);
            onlyInDb.removeAll(redisTemplateProdTypes);
            for (String prodType : onlyInDb) {
                AaListParamsStdModel template = dbModelMap.get(prodType);
                if (template != null) {
                    redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, template);
                    // 模版信息
                    boolean contained = redisModelInfoKeys.contains(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                    if (Boolean.FALSE.equals(contained)) {
                        dbModelInfos.forEach(info -> {
                            if (info.getProdType().equals(prodType)) {
                                try {
                                    redisTemplateInfo.opsForValue().set(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType, objectMapper.writeValueAsString(info));
                                } catch (JsonProcessingException e) {
                                    log.error(">>>>> JSON解析失败, msg: {}", info, e);
                                }
                                redisModelInfoKeys.add(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                            }
                        });
                    }
                    log.info("Added to Redis: {}", prodType);
                }
            }

            // Redis 中有而数据库中没有的模板
            Set<String> onlyInRedis = new HashSet<>(redisTemplateProdTypes);
            onlyInRedis.removeAll(dbTemplateProdTypes);
            for (String prodType : onlyInRedis) {
                redisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                // 模版信息
                boolean contained = redisModelKeys.contains(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                if (Boolean.TRUE.equals(contained)) {
                    redisTemplateInfo.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                    redisModelInfoKeys.remove(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                }
                log.info("Deleted from Redis: {}", prodType);
            }

            // 数据库和 Redis 中都有的模板
            Set<String> inBoth = new HashSet<>(dbTemplateProdTypes);
            inBoth.retainAll(redisTemplateProdTypes);
            for (String prodType : inBoth) {
                AaListParamsStdModel dbModel = dbModelMap.get(prodType);
                AaListParamsStdModel redisModel = redisModels.get(prodType);
                if (dbModel != null && redisModel != null && !dbModel.equals(redisModel)) {
                    redisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                    redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, dbModel);
                    log.info("Updated in Redis: {}", prodType);
                }
            }

            // 处理模版信息
            for (AaListParamsStdModelInfo info : dbModelInfos) {
                String key = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + info.getProdType();
                if (info.getStatus() == 1) {
                    // 如果 status 为 1，确保键存在于 Redis 中
                    if (!redisModelInfoKeys.contains(key)) {
                        redisTemplateInfo.opsForValue().set(key, objectMapper.writeValueAsString(info));
                        log.info("Added key {}to Redis", key);
                    }
                }
            }
            keysToKeep.addAll(redisModelInfoKeys);
            if (!keysToKeep.isEmpty()) {
                redisModelInfoKeys.removeAll(keysToKeep);
                redisTemplateInfo.delete(redisModelInfoKeys);
                log.info("Removed unnecessary keys from Redis: {}", redisModelInfoKeys);
            }
        } catch (Exception e) {
            log.error("Error during synchronization", e);
        }
    }
}
