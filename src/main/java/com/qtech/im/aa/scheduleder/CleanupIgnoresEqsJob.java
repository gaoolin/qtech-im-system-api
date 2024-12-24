package com.qtech.im.aa.scheduleder;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.redis.RedisCache;
import com.qtech.im.aa.domain.AaListParamsEqCtrl;
import com.qtech.im.aa.service.IAaListParamsEqCtrlService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.qtech.share.aa.constant.ComparisonConstants.EQ_REVERSE_IGNORE_SIM_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/12 09:10:49
 * desc   :
 */

@Slf4j
@Component
@EnableScheduling
@DataSource(DataSourceType.SECOND)
public class CleanupIgnoresEqsJob {
    private final RedisCache redisCache;
    private final RedissonClient redissonClient;
    private final IAaListParamsEqCtrlService aaListParamsEqCtrlService;

    @Autowired
    public CleanupIgnoresEqsJob(RedisCache redisCache, IAaListParamsEqCtrlService aaListParamsEqCtrlService, RedissonClient redissonClient) {
        this.redisCache = redisCache;
        this.aaListParamsEqCtrlService = aaListParamsEqCtrlService;
        this.redissonClient = redissonClient;
    }

    // @Scheduled(cron = "0 0 21 * * ?") // 每天晚上21:00执行
    public void cleanupDayShiftIgnores() {
        refreshEqStatus();
    }

    // @Scheduled(cron = "0 0 9 * * ?") // 每天早上09:00执行
    // @Scheduled(cron = "0/30 * * * * ?")
    public void cleanupNightShiftIgnores() {
        refreshEqStatus();
    }

    public void cleanupIgnores() {
    }

    private void refreshEqStatus() {
        Collection<String> keys = redisCache.keys(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
        redisCache.deleteObject(keys);
        UpdateWrapper<AaListParamsEqCtrl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("status", 1).set("status", 0);
        aaListParamsEqCtrlService.update(null, updateWrapper);
    }

    @Scheduled(cron = "0 * * * * ?") // 每分钟检查一次数据一致性
    public void checkAndRepairDataConsistency() {
        RLock lock = redissonClient.getLock("data_consistency_check_lock");
        try {
            if (lock.tryLock()) {
                log.info("Acquired lock for data consistency check");
                performDataConsistencyCheck();
            } else {
                log.warn("Failed to acquire lock for data consistency check");
            }
        } catch (Exception e) {
            log.error("Error during data consistency check", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("Released lock for data consistency check");
            }
        }
    }

    private void performDataConsistencyCheck() {
        try {
            // 获取数据库中的所有相关记录
            List<AaListParamsEqCtrl> dbRecords = aaListParamsEqCtrlService.list();

            // 获取 Redis 中的所有键
            Collection<String> redisKeys = redisCache.keys(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
            Set<String> redisKeySet = new HashSet<>(redisKeys);

            // 需要保留的键集合
            Set<String> keysToKeep = new HashSet<>();

            // 处理数据库中的记录
            for (AaListParamsEqCtrl record : dbRecords) {
                String key = EQ_REVERSE_IGNORE_SIM_PREFIX + record.getSimId();
                if (record.getStatusCode() == 1) {
                    // 如果 status 为 1，确保键存在于 Redis 中
                    if (!redisKeySet.contains(key)) {
                        redisCache.setCacheObject(key, "true");
                        log.info("Added key {} to Redis", key);
                    }
                    keysToKeep.add(key);
                }
            }

            // 删除 Redis 中不需要的键
            redisKeySet.removeAll(keysToKeep);
            if (!redisKeySet.isEmpty()) {
                redisCache.deleteObject(redisKeySet);
                log.info("Removed unnecessary keys from Redis: {}", redisKeySet);
            }

            log.info("Data consistency check completed successfully");
        } catch (Exception e) {
            log.error("Error during data consistency check and repair", e);
        }
    }
}