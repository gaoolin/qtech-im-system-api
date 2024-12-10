package com.qtech.im.wb.service.impl;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpPercentage;
import com.qtech.im.wb.mapper.WbOlpRatioMapper;
import com.qtech.im.wb.service.IWbOlpRatioService;
import com.qtech.im.wb.vo.WbOlpPercentageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 08:35:02
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class WbOlpRatioServiceImpl implements IWbOlpRatioService {

    @Autowired
    private WbOlpRatioMapper wbOlpRatioMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos getRatio(WbOlpPercentage wbOlpPercentage) {

        try {
            ArrayList<WbOlpPercentageVo> vos = new ArrayList<>();
            List<WbOlpPercentage> list = wbOlpRatioMapper.selectWbOlpRatioList(wbOlpPercentage);
            long total = new PageInfo(list).getTotal();
            list.forEach(item -> vos.add(new WbOlpPercentageVo(item)));
            return new QtechImVoUtil.QtechImVos(vos, total);
        } catch (Exception e) {
            log.error("getRatio error" , e);
            throw new RuntimeException(e);
        }
    }
}
