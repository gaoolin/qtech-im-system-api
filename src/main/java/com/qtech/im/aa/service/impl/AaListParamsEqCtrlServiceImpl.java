package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.framework.redis.RedisCache;
import com.qtech.framework.security.LoginUser;
import com.qtech.im.aa.domain.AaListParamsEqCtrl;
import com.qtech.im.aa.exception.AaListParamsException;
import com.qtech.im.aa.mapper.AaListParamsEqCtrlMapper;
import com.qtech.im.aa.service.IAaListParamsEqCtrlService;
import com.qtech.im.aa.utils.Status;
import com.qtech.im.aa.vo.AaListParamsEqCtrlVo;
import com.qtech.project.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.qtech.common.utils.SecurityUtils.getLoginUser;
import static com.qtech.im.aa.utils.ErrorCode.INVALID_INPUT;
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
public class AaListParamsEqCtrlServiceImpl extends ServiceImpl<AaListParamsEqCtrlMapper, AaListParamsEqCtrl> implements IAaListParamsEqCtrlService {
    @Autowired
    private RedisCache redisCache;

    @Override
    public List<AaListParamsEqCtrlVo> list(AaListParamsEqCtrlVo aaListParamsEqCtrlVo) {
        try {
            return baseMapper.listFullInfo(aaListParamsEqCtrlVo);
        } catch (Exception e) {
            log.error("selectAaListParamsEqList error", e);
            throw new RuntimeException("查询后台数据库发生异常，请联系管理员！");
        }
    }

    @Override
    public AaListParamsEqCtrl getById(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("id is null, can not be updated");
            }
            return baseMapper.selectById(id);
        } catch (Exception e) {
            log.error("selectAaListParamsEqById error", e);
            throw new RuntimeException("查询后台数据库发生异常，请联系管理员！");
        }
    }

    @Override
    public Boolean edit(AaListParamsEqCtrl aaListParamsEqCtrl) {
        commonEdit(aaListParamsEqCtrl);
        return baseMapper.updateSimIdAndSource(aaListParamsEqCtrl) > 0;
    }

    private void commonEdit(AaListParamsEqCtrl aaListParamsEqCtrl) {
        Integer status = aaListParamsEqCtrl.getStatusCode();
        if (status == null) {
            throw new AaListParamsException("status is null, can not be updated", INVALID_INPUT);
        }
        Status statusEnum = Status.fromCode(status);
        switch (statusEnum) {
            case ACTIVE:
                redisCache.setCacheObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqCtrl.getSimId(), "true");
                break;
            case INACTIVE:
                redisCache.deleteObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqCtrl.getSimId());
                break;
            default:
                throw new AaListParamsException("unknown status", INVALID_INPUT);
        }
        aaListParamsEqCtrl.setUpdateDt(LocalDateTime.now());
        aaListParamsEqCtrl.setUpdateBy(getLoginUser().getUser().getNickName());
    }

    /**
     * @param aaListParamsEqCtrl
     * @return int
     * @description 主要用于插入数据和更新除了状态以外的数据
     */
    @Override
    public Boolean addOne(AaListParamsEqCtrl aaListParamsEqCtrl) {
        if (aaListParamsEqCtrl != null) {
            // 获取当前登录用户的昵称
            String nickName = Optional.ofNullable(getLoginUser()).map(LoginUser::getUser).map(SysUser::getNickName).orElseThrow(() -> new RuntimeException("获取当前登录用户失败"));

            aaListParamsEqCtrl.setUpdateDt(LocalDateTime.now());
            aaListParamsEqCtrl.setUpdateBy(nickName);
            aaListParamsEqCtrl.setOpCnt(1);

            redisCache.setCacheObject(EQ_REVERSE_IGNORE_SIM_PREFIX + aaListParamsEqCtrl.getSimId(), "true");

            try {
                return save(aaListParamsEqCtrl);
            } catch (Exception e) {
                log.error("upsetAaListParamsEq error", e);
                throw new RuntimeException("添加数据发生异常，请联系管理员！");
            }
        }
        return false;
    }

    @Override
    @Scheduled(cron = "0 0 21 * * ?") // 每天晚上21:00执行
    public void cleanupDayShiftIgnores() {
        Collection<String> keys = redisCache.keys(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    @Scheduled(cron = "0 0 9 * * ?") // 每天早上09:00执行
    public void cleanupNightShiftIgnores() {
        Collection<String> keys = redisCache.keys(EQ_REVERSE_IGNORE_SIM_PREFIX + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    public void cleanupIgnores() {

    }

    @Override
    public Boolean isExist(AaListParamsEqCtrl aaListParamsEqCtrl) {
        QueryWrapper<AaListParamsEqCtrl> wrapper = new QueryWrapper<>();
        if (aaListParamsEqCtrl.getSimId() != null) {
            wrapper.eq("SIM_ID", aaListParamsEqCtrl.getSimId());
            wrapper.eq("SOURCE", "aa-list");
            return baseMapper.exists(wrapper);
        } else {
            return false;
        }
    }
}
