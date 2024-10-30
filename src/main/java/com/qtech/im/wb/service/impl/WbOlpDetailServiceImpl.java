package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.domain.WbOlpParticularsVo;
import com.qtech.im.wb.mapper.WbOlpDetailMapper;
import com.qtech.im.wb.service.IWbOlpDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 14:27:05
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpDetailServiceImpl implements IWbOlpDetailService {

    @Autowired
    private WbOlpDetailMapper wbOlpDetailMapper;
    @Override
    public List<WbOlpParticularsVo> getDetail(WbOlpParticularsVo wbOlpParticularsVo) {
        try {
            return wbOlpDetailMapper.getDetail(wbOlpParticularsVo);
        } catch (Exception e) {
            log.error("查询wbOlpParticularsVo异常", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人！");
        }
    }
}
