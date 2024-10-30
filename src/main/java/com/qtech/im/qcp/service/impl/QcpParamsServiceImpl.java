package com.qtech.im.qcp.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.qcp.domain.QcpParamsDetailVo;
import com.qtech.im.qcp.domain.QcpParamsVo;
import com.qtech.im.qcp.mapper.QcpParamsMapper;
import com.qtech.im.qcp.service.IQcpParamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:52:30
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.THIRD)
@Service
public class QcpParamsServiceImpl implements IQcpParamsService {

    @Autowired
    private QcpParamsMapper qcpParamsMapper;

    @Override
    public List<QcpParamsVo> selectQcpParamsOverviewList(QcpParamsVo qcpParamsVo) {
        qcpParamsVo.setDt(LocalDateTime.now());
        try {
            return qcpParamsMapper.selectQcpParamsOverviewList(qcpParamsVo);
        } catch (Exception e) {
            log.error("查询QCP参数概览列表失败", e);
            throw new RuntimeException("查询QCP参数概览列表失败，请联系系统负责人!");
        }
    }

    @Override
    public List<QcpParamsDetailVo> selectQcpParamsList(QcpParamsDetailVo qcpParamsDetailVo) {
        try {
            return qcpParamsMapper.selectQcpParamsList(qcpParamsDetailVo);
        } catch (Exception e) {
            log.error("查询QCP参数列表失败", e);
            throw new RuntimeException("查询QCP参数列表失败，请联系系统负责人!");
        }
    }

    @Override
    public String getMaxTime() {
        try {
            return qcpParamsMapper.getMaxTime();
        } catch (Exception e) {
            log.error("获取最大时间失败", e);
            throw new RuntimeException("获取最大时间失败，请联系系统负责人!");
        }
    }
}
