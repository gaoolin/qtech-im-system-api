package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.domain.WbOlpTrending;
import com.qtech.im.wb.mapper.WbOlpIndexMapper;
import com.qtech.im.wb.service.IWbOlpIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/01 12:14:59
 * desc   :
 */
@DataSource(DataSourceType.SECOND)
@Slf4j
@Service
public class WbOlpIndexServiceImpl implements IWbOlpIndexService {

    @Autowired
    private WbOlpIndexMapper wbOlpIndexMapper;


    @DataSource(DataSourceType.FOURTH)
    @Override
    public Long getModelAvgCnt() {
        try {
            return wbOlpIndexMapper.getModelAvgCnt();
        } catch (Exception e) {
            log.error("getModelAvgCnt error", e);
            throw new RuntimeException("查询智慧打线图平均线数，请联系系统负责人!");
        }
    }

    @DataSource(DataSourceType.FOURTH)
    @Override
    public Long getModelsTtlCnt() {
        try {
            return wbOlpIndexMapper.getModelsTtlCnt();
        } catch (Exception e) {
            log.error("getModelsTtlCnt error", e);
            throw new RuntimeException("查询智慧打线图比对总数，请联系系统负责人!");
        }
    }

    @Override
    public List<WbOlpTrending> getWbOlpTrending() {
        try {
            return wbOlpIndexMapper.getWbOlpTrending();
        } catch (Exception e) {
            log.error("getWbOlpTrending error", e);
            throw new RuntimeException("查询智慧打线图趋势图，请联系系统负责人!");
        }
    }
}
