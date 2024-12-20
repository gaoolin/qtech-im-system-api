package com.qtech.im.aa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsParsed;
import com.qtech.im.aa.mapper.AaListParamsParsedDetailMapper;
import com.qtech.im.aa.service.IAaListParamsParsedDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AaListParamsParsedDetailServiceImpl extends ServiceImpl<AaListParamsParsedDetailMapper, AaListParamsParsed> implements IAaListParamsParsedDetailService {
    private static final Logger logger = LoggerFactory.getLogger(AaListParamsParsedDetailServiceImpl.class);

    @Override
    public List<AaListParamsParsed> selectParsedDetailList(AaListParamsParsed aaListParamsParsed) {

        try {
            LambdaQueryWrapper<AaListParamsParsed> wrapper = new LambdaQueryWrapper<>();
            if (aaListParamsParsed.getProdType() != null) {
                wrapper.eq(AaListParamsParsed::getProdType, aaListParamsParsed.getProdType());
            }
            if (aaListParamsParsed.getSimId() != null) {
                wrapper.eq(AaListParamsParsed::getSimId, aaListParamsParsed.getSimId());
            }
            if (!aaListParamsParsed.getParams().isEmpty()) {
                wrapper.between(AaListParamsParsed::getReceivedTime, aaListParamsParsed.getParams().get("beginTime"), aaListParamsParsed.getParams().get("endTime"));
            }
            wrapper.orderByDesc(AaListParamsParsed::getReceivedTime);
            return list(wrapper);
        } catch (Exception e) {
            logger.error("selectParsedDetailList error", e);
            throw new RuntimeException("查询数据时发生异常，请联系系统管理员！");
        }
    }
}
