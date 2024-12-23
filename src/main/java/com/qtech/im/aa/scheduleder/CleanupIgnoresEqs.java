package com.qtech.im.aa.scheduleder;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.redis.RedisCache;
import com.qtech.im.aa.domain.AaListParamsEqCtrl;
import com.qtech.im.aa.service.IAaListParamsEqCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.qtech.share.aa.constant.ComparisonConstants.EQ_REVERSE_IGNORE_SIM_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/12 09:10:49
 * desc   :
 */

@Component
// @EnableScheduling
public class CleanupIgnoresEqs {
    private final RedisCache redisCache;
    private final IAaListParamsEqCtrlService aaListParamsEqCtrlService;

    @Autowired
    public CleanupIgnoresEqs(RedisCache redisCache, IAaListParamsEqCtrlService aaListParamsEqCtrlService) {
        this.redisCache = redisCache;
        this.aaListParamsEqCtrlService = aaListParamsEqCtrlService;
    }

    @Scheduled(cron = "0 0 21 * * ?") // 每天晚上21:00执行
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

    @DataSource(DataSourceType.SECOND)
    private void refreshEqStatus() {
        Collection<String> keys = redisCache.keys(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
        redisCache.deleteObject(keys);
        UpdateWrapper<AaListParamsEqCtrl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("status", 1).set("status", 0);
        aaListParamsEqCtrlService.update(null, updateWrapper);
    }
}