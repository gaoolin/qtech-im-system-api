package com.qtech.im.aa.scheduleder;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
public class synchronizeTemplatesJob {
    @Autowired
    private IAaListParamsStdModelInfoService aaListParamsStdModelInfoService;
    @Autowired
    private IAaListParamsStdModelService aaListParamsStdModelService;
    @Autowired
    private RedisTemplate<String, AaListParamsStdModel> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplateInfo;
    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(cron = "0 * * * * ?") // 每分钟检查一次数据一致性
    public void synchronizeTemplates() {
        // 从数据库中获取所有模板
        Map<String, AaListParamsStdModel> dbModelMap = new HashMap<String, AaListParamsStdModel>();
        List<AaListParamsStdModel> models = aaListParamsStdModelService.list();
        if (models != null) {
            for (AaListParamsStdModel template : models) {
                if (template.getProdType() != null) {
                    dbModelMap.put(template.getProdType(), template);
                }
            }
        }
        // 假设这是你的方法
        List<AaListParamsStdModelInfo> dbModelInfos = aaListParamsStdModelInfoService.list(); // 假设这是你的方法

        // 模版信息
        Set<String> keysToKeep = new CopyOnWriteArraySet<>();

        // 从 Redis 中获取所有模板
        final Set<String> redisModelKeys = new CopyOnWriteArraySet<>();
        final Set<String> redisModelInfoKeys = new CopyOnWriteArraySet<>();
        ScanOptions options = ScanOptions.scanOptions().match(REDIS_COMPARISON_MODEL_KEY_PREFIX + "*").build();

        redisTemplate.execute((RedisCallback<Void>) connection -> {
            try (Cursor<byte[]> cursor = connection.scan(options)) {
                while (cursor.hasNext()) {
                    String key = new String(cursor.next());
                    if (key.startsWith(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX)) {
                        redisModelInfoKeys.add(key);
                    } else {
                        redisModelKeys.add(key);
                    }
                }
            }
            return null;
        });

        Map<String, AaListParamsStdModel> redisModels = new ConcurrentHashMap<>();
        if (!redisModelKeys.isEmpty()) {
            List<AaListParamsStdModel> list = redisTemplate.opsForValue().multiGet(new ArrayList<>(redisModelKeys));
            if (list != null) {
                for (AaListParamsStdModel template : list) {
                    if (template != null) {
                        redisModels.put(template.getProdType(), template);
                    }
                }
            }
        }

        // 数据库中的模板 机型 集合
        Set<String> dbTemplateProdTypes;
        dbTemplateProdTypes = dbModelMap.keySet();

        // Redis 中的模板 机型 集合
        Set<String> redisTemplateProdTypes = redisModels.keySet();

        // 数据库中有而 Redis 中没有的模板
        Set<String> onlyInDb = null;
        onlyInDb = new HashSet<>(dbTemplateProdTypes);
        onlyInDb.removeAll(redisTemplateProdTypes);
        for (String prodType : onlyInDb) {
            AaListParamsStdModel template = dbModelMap.get(prodType);
            if (template != null) {
                redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, template);
                // 模版信息
                String infoKey = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
                AaListParamsStdModelInfo info = findModelInfoByProdType(dbModelInfos, prodType);
                if (info != null) {
                    try {
                        redisTemplateInfo.opsForValue().set(infoKey, objectMapper.writeValueAsString(info));
                    } catch (JsonProcessingException e) {
                        log.error("JSON解析失败, msg: {}", info, e);
                    }
                    redisModelInfoKeys.add(infoKey);
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
            String infoKey = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
            if (redisModelKeys.contains(infoKey)) {
                redisTemplateInfo.delete(infoKey);
                redisModelInfoKeys.remove(infoKey);
            }
            log.info("Deleted from Redis: {}", prodType);
        }

        // 数据库和 Redis 中都有的模板
        Set<String> inBoth = null;
        inBoth = new HashSet<>(dbTemplateProdTypes);
        inBoth.retainAll(redisTemplateProdTypes);
        for (String prodType : inBoth) {
            AaListParamsStdModel dbModel = dbModelMap.get(prodType);
            AaListParamsStdModel redisModel = redisModels.get(prodType);
            if (dbModel != null && redisModel != null && !dbModel.equals(redisModel)) {
                redisTemplate.delete(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                redisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, dbModel);
                log.info("Updated in Redis: {}", prodType);
            }

            // 模版信息
            String infoKey = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
            AaListParamsStdModelInfo dbInfo = findModelInfoByProdType(dbModelInfos, prodType);
            AaListParamsStdModelInfo redisInfo = findModelInfoByProdType(redisModelInfoKeys, prodType);
            if (dbInfo != null && redisInfo != null && !dbInfo.equals(redisInfo)) {
                try {
                    redisTemplateInfo.opsForValue().set(infoKey, objectMapper.writeValueAsString(dbInfo));
                    log.info("Updated model info in Redis: {}", prodType);
                } catch (JsonProcessingException e) {
                    log.error("JSON解析失败, msg: {}", dbInfo, e);
                }
            } else if (dbInfo != null && redisInfo == null) {
                try {
                    redisTemplateInfo.opsForValue().set(infoKey, objectMapper.writeValueAsString(dbInfo));
                    redisModelInfoKeys.add(infoKey);
                    log.info("Added model info to Redis: {}", prodType);
                } catch (JsonProcessingException e) {
                    log.error("JSON解析失败, msg: {}", dbInfo, e);
                }
            } else if (dbInfo == null && redisInfo != null) {
                redisTemplateInfo.delete(infoKey);
                redisModelInfoKeys.remove(infoKey);
                log.info("Deleted model info from Redis: {}", prodType);
            }
        }

        // 同步数据库中的 model info 数据
        Set<String> dbModelInfoProdTypes = new HashSet<>();
        for (AaListParamsStdModelInfo info : dbModelInfos) {
            dbModelInfoProdTypes.add(info.getProdType());
        }

        // 数据库中有而 dbModelInfos 中没有的 model info
        Set<String> onlyInDbModelInfos = null;
        onlyInDbModelInfos = new HashSet<>(dbTemplateProdTypes);
        onlyInDbModelInfos.removeAll(dbModelInfoProdTypes);
        for (String prodType : onlyInDbModelInfos) {
            AaListParamsStdModelInfo info = findModelInfoByProdType(dbModelInfos, prodType);
            if (info != null) {
                aaListParamsStdModelInfoService.save(info); // 假设这是你的方法
                log.info("Added to dbModelInfos: {}", prodType);
            }
        }

        // dbModelInfos 中有而数据库中没有的 model info
        Set<String> onlyInDbModelInfosDb = new HashSet<>(dbModelInfoProdTypes);
        onlyInDbModelInfosDb.removeAll(dbTemplateProdTypes);
        for (String prodType : onlyInDbModelInfosDb) {
            LambdaQueryWrapper<AaListParamsStdModelInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AaListParamsStdModelInfo::getProdType, prodType);
            aaListParamsStdModelInfoService.remove(wrapper);
            log.info("Deleted from dbModelInfos: {}", prodType);
        }

        // 数据库和 dbModelInfos 中都有的 model info
        Set<String> inBothDbModelInfos = null;
        inBothDbModelInfos = new HashSet<>(dbTemplateProdTypes);
        inBothDbModelInfos.retainAll(dbModelInfoProdTypes);
        for (String prodType : inBothDbModelInfos) {
            AaListParamsStdModelInfo dbInfo = findModelInfoByProdType(dbModelInfos, prodType);
            LambdaQueryWrapper<AaListParamsStdModelInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(AaListParamsStdModelInfo::getProdType, prodType);
            AaListParamsStdModelInfo dbModelInfo = aaListParamsStdModelInfoService.getOne(wrapper); // 假设这是你的方法
            if (dbInfo != null && dbModelInfo != null && !dbInfo.equals(dbModelInfo)) {
                aaListParamsStdModelInfoService.save(dbInfo); // 假设这是你的方法
                log.info("Updated in dbModelInfos: {}", prodType);
            }
        }

        // 删除不必要的键
        redisTemplateInfo.delete(redisModelInfoKeys.stream().filter(key -> !keysToKeep.contains(key)).collect(Collectors.toSet()));
        log.info("Removed unnecessary keys from Redis: {}", redisModelInfoKeys);
    }

    private AaListParamsStdModelInfo findModelInfoByProdType(List<AaListParamsStdModelInfo> dbModelInfos, String prodType) {
        for (AaListParamsStdModelInfo info : dbModelInfos) {
            if (info.getProdType().equals(prodType)) {
                return info;
            }
        }
        return null;
    }

    private AaListParamsStdModelInfo findModelInfoByProdType(Set<String> redisModelInfoKeys, String prodType) {
        String key = REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType;
        if (redisModelInfoKeys.contains(key)) {
            try {
                String value = redisTemplateInfo.opsForValue().get(key);
                return objectMapper.readValue(value, AaListParamsStdModelInfo.class);
            } catch (JsonProcessingException e) {
                log.error("JSON解析失败, key: {}", key, e);
            }
        }
        return null;
    }
}