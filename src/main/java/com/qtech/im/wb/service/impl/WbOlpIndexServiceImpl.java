package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.mapper.WbOlpIndexMapper;
import com.qtech.im.wb.service.IWbOlpIndexService;
import com.qtech.im.wb.vo.WbOlpChkVo;
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
    @Override
    public List<WbOlpChkVo> selectFactoryNameList() {
        try {
            return wbOlpIndexMapper.selectFactoryNameList();
        } catch (Exception e) {
            log.error("查询wbOlpOverviewList异常", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }

    @Override
    public List<WbOlpChkVo> selectWorkShopNameList(WbOlpChkVo wbOlpChkVo) {
        try {
            return wbOlpIndexMapper.selectWorkShopNameList(wbOlpChkVo);
        } catch (Exception e) {
            log.error("查询wbOlpOverviewList异常", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}
