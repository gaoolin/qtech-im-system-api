package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsIndexVo;
import com.qtech.im.aa.mapper.AaListParamsIndexResultMapper;
import com.qtech.im.aa.service.IAaListParamsIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 14:38:46
 * desc   :
 */

@DataSource(DataSourceType.SECOND)
@Service
public class AaLIstParamsIndexServiceImpl implements IAaListParamsIndexService {

    @Autowired
    private AaListParamsIndexResultMapper aaListParamsIndexResultMapper;

    @Override
    public List<AaListParamsIndexVo> selectAaListParamsIndexResultList(AaListParamsIndexVo aaListParamsIndexVo) {
        try {
            return aaListParamsIndexResultMapper.list(aaListParamsIndexVo);
        } catch (Exception e) {
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人！");
        }
    }

    @Override
    public String updateTime() {
        try {
            LocalDateTime updateDt = aaListParamsIndexResultMapper.updateTime();
            if (updateDt == null) {
                return "--";
            }
            return updateDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            throw new RuntimeException("查询数据库发生异常，请联系系统负责人！");
        }
    }
}