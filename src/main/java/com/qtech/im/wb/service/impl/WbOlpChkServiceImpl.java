package com.qtech.im.wb.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.mapper.WbOlpChkMapper;
import com.qtech.im.wb.service.IWbOlpChkService;
import com.qtech.im.wb.vo.WbOlpChkVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<WbOlpChkVo> selectWbOlpChkList(WbOlpChk wbOlpChk, int daysFilter) {
        try {
            ArrayList<WbOlpChkVo> vos = new ArrayList<>();
            List<WbOlpChk> list = wbOlpChkMapper.selectWbOlpChkList(wbOlpChk, daysFilter);
            long total = new PageInfo(list).getTotal();
            list.forEach(item -> vos.add(new WbOlpChkVo(item)));
            return new QtechImVoUtil.QtechImVos(vos, total);
        } catch (Exception e) {
            log.error("selectWbOlpChkList error", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人!");
        }
    }
}
