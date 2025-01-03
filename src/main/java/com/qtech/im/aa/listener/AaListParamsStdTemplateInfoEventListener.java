package com.qtech.im.aa.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qtech.im.aa.domain.template.AaListParamsStdTemplateInfo;
import com.qtech.im.aa.event.AaListParamsStdTemplateInfoEvent;
import com.qtech.im.aa.service.IAaListParamsStdTemplateInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.qtech.share.aa.constant.ComparisonConstants.REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/27 16:39:28
 * desc   :
 */

@Slf4j
@Component
public class AaListParamsStdTemplateInfoEventListener {
    @Autowired
    private RedisTemplate<String, Object> imRedisTemplate;
    @Autowired
    private IAaListParamsStdTemplateInfoService aaListParamsStdTemplateInfoService;

    @EventListener
    public void handleAaListParamsStdTemplateInfoEvent(AaListParamsStdTemplateInfoEvent event) {
        String prodType = event.getProdType();
        String operation = event.getOperation();
        LambdaQueryWrapper<AaListParamsStdTemplateInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AaListParamsStdTemplateInfo::getProdType, prodType);
        AaListParamsStdTemplateInfo one = aaListParamsStdTemplateInfoService.getOne(wrapper);

        try {
            switch (operation) {
                case "INSERT":
                    log.info("Inserting Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    imRedisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType, one);
                    break;
                case "UPDATE":
                    log.info("Updating Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    imRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                    imRedisTemplate.opsForValue().set(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType, one);
                    break;
                case "DELETE":
                    log.info("Deleting Redis cache for prodType: {}", prodType);
                    log.info("Source: {}", event.getSource());
                    imRedisTemplate.delete(REDIS_COMPARISON_MODEL_INFO_KEY_SUFFIX + prodType);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("Error handling AaListParamsStdTemplateInfoEvent for prodType: {}", prodType, e);
            // 可以考虑在这里实现重试机制
        }
    }
}