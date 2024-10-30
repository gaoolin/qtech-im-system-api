package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.ImAaListParamsParsed;
import com.qtech.im.aa.mapper.AaListParamsParsedDetailMapper;
import com.qtech.im.aa.service.IAaListParamsParsedDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 15:59:10
 * desc   :
 */

@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsParsedDetailServiceImpl implements IAaListParamsParsedDetailService {
    private static final Logger logger = LoggerFactory.getLogger(AaListParamsParsedDetailServiceImpl.class);

    @Autowired
    private AaListParamsParsedDetailMapper aaListParamsParsedDetailMapper;

    @Override
    public List<ImAaListParamsParsed> selectAaListParamsParsedDetailList(ImAaListParamsParsed aaListParamsParsed) {
        List<ImAaListParamsParsed> list = null;
        try {
            list = aaListParamsParsedDetailMapper.selectAaListParamsParsedDetailList(aaListParamsParsed);
            for (ImAaListParamsParsed listParamsParsed : list) {
                System.out.println(listParamsParsed);
            }
        } catch (Exception e) {
            logger.error("selectAaListParamsParsedDetailList error", e);
        }
        return list;
    }
}
