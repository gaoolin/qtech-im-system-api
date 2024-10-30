package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.wb.mapper.WbOlpChkMapper;
import com.qtech.im.wb.service.IWbOlpChkService;
import com.qtech.im.wb.vo.WbOlpChkVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 16:33:06
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpChkServiceImpl implements IWbOlpChkService {

    @Autowired
    private WbOlpChkMapper wbOlpChkMapper;

    @Override
    public List<WbOlpChkVo> selectWbOlpChkList(WbOlpChkVo vo, int daysFilter) {
        try {
            return wbOlpChkMapper.selectWbOlpChkList(vo, daysFilter);
        } catch (Exception e) {
            log.error("selectWbOlpChkList error", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}
