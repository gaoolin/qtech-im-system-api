package com.qtech.im.aa.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qtech.framework.redis.RedisCache;
import com.qtech.im.aa.domain.AaListParamsStdTemplate;
import com.qtech.im.aa.event.AaListParamsStdTemplateEvent;
import com.qtech.im.aa.service.IAaListParamsStdTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_KEY_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/27 14:47:26
 * desc   :  事件监听器
 */

@Slf4j
@Component
public class AaListParamsStdTemplateEventListener {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IAaListParamsStdTemplateService aaListParamsStdTemplateService;

    @EventListener
    public void handleAaListParamsStdTemplateEvent(AaListParamsStdTemplateEvent event) {
        String prodType = event.getProdType();
        String operation = event.getOperation();
        LambdaQueryWrapper<AaListParamsStdTemplate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AaListParamsStdTemplate::getProdType, prodType);
        AaListParamsStdTemplate one = aaListParamsStdTemplateService.getOne(queryWrapper);

        try {
            switch (operation) {
                case "INSERT":
                    log.info("Inserting Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    // 这里可以根据需要实现插入 Redis 缓存的逻辑
                    redisCache.setCacheObject(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, one);
                    break;
                case "UPDATE":
                    log.info("Updating Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    // 这里可以根据需要实现更新 Redis 缓存的逻辑
                    redisCache.deleteObject(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                    redisCache.setCacheObject(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType, one);
                    break;
                case "DELETE":
                    log.info("Deleting Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    redisCache.deleteObject(REDIS_COMPARISON_MODEL_KEY_PREFIX + prodType);
                    break;
                default:
                    log.warn("Unknown operation: {}", operation);
            }
        } catch (Exception e) {
            log.error("Error handling AaListParamsStdTemplateEvent for prodType: {}", prodType, e);
            // 可以考虑在这里实现重试机制
        }
    }
}
