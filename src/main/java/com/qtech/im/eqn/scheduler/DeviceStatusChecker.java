package com.qtech.im.eqn.scheduler;

import com.qtech.im.eqn.domain.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/11/19 09:49:29
 * desc   :
 */

@Component
public class DeviceStatusChecker {

    private static final String DEVICE_STATUS_PREFIX = "device_status:";
    @Autowired
    @Qualifier("iotRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 60000) // 每分钟检查一次
    public void checkDeviceStatus() {
        Set<String> keys = redisTemplate.keys(DEVICE_STATUS_PREFIX + "*");
        if (keys == null) return;

        LocalDateTime now = LocalDateTime.now();
        for (String key : keys) {
            DeviceData deviceData = (DeviceData) redisTemplate.opsForValue().get(key);
            if (deviceData != null && deviceData.getLastUpdated().isBefore(now.minusMinutes(5))) {
                deviceData.setStatus("OFFLINE");
                redisTemplate.opsForValue().set(key, deviceData, 6, TimeUnit.MINUTES);
                System.out.println("Device marked as OFFLINE: " + deviceData.getSimId());
            }
        }
    }
}
