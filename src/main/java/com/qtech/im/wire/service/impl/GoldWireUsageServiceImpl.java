package com.qtech.im.wire.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wire.domain.ImGoldWireUsage;
import com.qtech.im.wire.mapper.GoldWireUsageMapper;
import com.qtech.im.wire.service.IGoldWireUsageService;
import com.qtech.im.wire.vo.ImGoldWireEqUsageVo;
import com.qtech.im.wire.vo.ImGoldWireFactoryUsageVo;
import com.qtech.im.wire.vo.ImGoldWireGroupUsageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/31 16:23:08
 * desc   :
 */

@Slf4j
@DataSource(value = DataSourceType.SECOND)
@Service
public class GoldWireUsageServiceImpl implements IGoldWireUsageService {

    @Autowired
    private GoldWireUsageMapper goldWireUsageMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<ImGoldWireEqUsageVo> selectEqLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage) {
        try {
            ArrayList<ImGoldWireEqUsageVo> vos = new ArrayList<>();
            List<ImGoldWireUsage> list = goldWireUsageMapper.selectEqLevelWireUsageList(eqLevelWireUsage);
            long total = new PageInfo(list).getTotal();
            list.forEach(item -> vos.add(new ImGoldWireEqUsageVo(item)));
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("selectEqLevelWireUsageList:", e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<ImGoldWireGroupUsageVo> selectGroupLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage) {
        try {
            ArrayList<ImGoldWireGroupUsageVo> vos = new ArrayList<>();
            List<ImGoldWireUsage> list = goldWireUsageMapper.selectGroupLevelWireUsageList(eqLevelWireUsage);
            long total = new PageInfo(list).getTotal();
            list.forEach(item -> vos.add(new ImGoldWireGroupUsageVo(item)));
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("selectGroupLevelWireUsageList:", e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<ImGoldWireFactoryUsageVo> selectFactoryLevelWireUsageList(ImGoldWireUsage eqLevelWireUsage) {
        try {
            ArrayList<ImGoldWireFactoryUsageVo> vos = new ArrayList<>();
            List<ImGoldWireUsage> list = goldWireUsageMapper.selectFactoryLevelWireUsageList(eqLevelWireUsage);
            long total = new PageInfo(list).getTotal();
            list.forEach(item -> vos.add(new ImGoldWireFactoryUsageVo(item)));
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("selectFactoryLevelWireUsageList:", e);
            throw new RuntimeException("系统处理数据发生异常，请联系系统负责人！");
        }
    }
}
