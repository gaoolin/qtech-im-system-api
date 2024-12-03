package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.redis.RedisCache;
import com.qtech.im.aa.domain.AaListParamsEqVo;
import com.qtech.im.aa.mapper.AaListParamsEqMapper;
import com.qtech.im.aa.service.IAaListParamsEqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;
import static com.qtech.share.aa.constant.ComparisonConstants.EQ_REVERSE_IGNORE_SIM_PREFIX;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:06:06
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsEqServiceImpl implements IAaListParamsEqService {
    private final AaListParamsEqMapper aaListParamsEqMapper;
    private final RedisCache redisCache;

    public AaListParamsEqServiceImpl(AaListParamsEqMapper aaListParamsEqMapper, RedisCache redisCache) {
        this.aaListParamsEqMapper = aaListParamsEqMapper;
        this.redisCache = redisCache;
    }

    @Override
    public List<AaListParamsEqVo> selectAaListParamsEqList(AaListParamsEqVo aaListParamsEqVo) {
        try {
            return aaListParamsEqMapper.selectAaListParamsEqList(aaListParamsEqVo);
        } catch (Exception e) {
            // 更具体的异常处理
            if (e instanceof NullPointerException) {
                log.error("NullPointerException in selectAaListParamsEqList: {}", e.getMessage());
                throw new RuntimeException("查询后台数据库时出现空指针异常，请检查参数是否为空！");
            } else if (e instanceof IllegalArgumentException) {
                log.error("IllegalArgumentException in selectAaListParamsEqList: {}", e.getMessage());
                throw new RuntimeException("查询后台数据库时参数非法，请检查输入参数！");
            } else {
                log.error("selectAaListParamsEqList error", e);
                throw new RuntimeException("查询后台数据库发生异常，请联系管理员！");
            }
        }
    }

    @Override
    public AaListParamsEqVo selectOneAaListParamsEq(AaListParamsEqVo aaListParamsEqVo) {
        List<AaListParamsEqVo> list = aaListParamsEqMapper.selectAaListParamsEqList(aaListParamsEqVo);
        if (CollectionUtils.isNotEmpty(list)) {
            int size = list.size();
            if (size > 1) {
                log.error("selectOneAaListParamsEq error, size > 1");
                throw new TooManyResultsException("selectOneAaListParamsEq error, size > 1");
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public AaListParamsEqVo selectAaListParamsEqById(String id) {
        try {
            return aaListParamsEqMapper.selectAaListParamsEqById(id);
        } catch (Exception e) {
            log.error("selectAaListParamsEqById error", e);
            throw new RuntimeException("查询后台数据库发生异常，请联系管理员！");
        }
    }

    @Override
    public int editAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo) {
        try {
            Integer status = aaListParamsEqVo.getStatusCode();
            if (status == null) {
                throw new RuntimeException("status is null, can not be updated");
            } else if (status == 1) {
                redisCache.setCacheObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqVo.getSimId(), "true");
            } else if (status == 0) {
                redisCache.deleteObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqVo.getSimId());
            } else {
                throw new RuntimeException("unknown status");
            }
            aaListParamsEqVo.setUpdateTime(Date.from(Instant.now()));
            aaListParamsEqVo.setUpdateBy(getLoginUser().getUser().getNickName());
            return aaListParamsEqMapper.editAaListParamsIgnoreEq(aaListParamsEqVo);
        } catch (Exception e) {
            log.error("editAaListParamsEq error", e);
            throw new RuntimeException("修改数据发生异常，请联系管理员！");
        }
    }

    /**
     * @param aaListParamsEqVo
     * @return int
     * @description 主要用于插入数据和更新除了状态以外的数据
     */
    @Override
    public int insertAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo) {
        if (aaListParamsEqVo != null) {
            aaListParamsEqVo.setUpdateTime(Date.from(Instant.now()));
            aaListParamsEqVo.setUpdateBy(getLoginUser().getUser().getNickName());
            aaListParamsEqVo.setOpCnt(1);
            redisCache.setCacheObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqVo.getSimId(), "true");
            try {
                return aaListParamsEqMapper.insertAaListParamsIgnoreEq(aaListParamsEqVo);
            } catch (Exception e) {
                log.error("upsetAaListParamsEq error", e);
                throw new RuntimeException("添加数据发生异常，请联系管理员！");
            }
        }
        return 0;
    }

    @Override
    @Scheduled(cron = "0 0 21 * * ?") // 每天晚上21:00执行
    public void cleanupDayShiftIgnores() {
        redisCache.deleteObject(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
    }

    @Override
    @Scheduled(cron = "0 0 9 * * ?") // 每天早上09:00执行
    public void cleanupNightShiftIgnores() {
        redisCache.deleteObject(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
    }

    @Override
    public void cleanupIgnores() {

    }

    @Override
    public int editAaListParamsEq(AaListParamsEqVo aaListParamsEqVo) {
        return aaListParamsEqMapper.editAaListParamsEq(aaListParamsEqVo);
    }

    @Override
    public Boolean isExist(AaListParamsEqVo aaListParamsEqVo) {
        AaListParamsEqVo res = selectAaListParamsEqById(aaListParamsEqVo.getSimId());
        return res != null;
    }
}
