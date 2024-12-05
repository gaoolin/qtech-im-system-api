package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.mapper.AaListParamsHistoryCheckResultMapper;
import com.qtech.im.aa.service.IAaListParamsHistoryCheckResultService;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:58:31
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsHistoryCheckResultServiceImpl implements IAaListParamsHistoryCheckResultService {

    @Autowired
    private AaListParamsHistoryCheckResultMapper aaListParamsHistoryCheckResultMapper;

    @Override
    public List<AaListParamsCheckResultVo> selectAaListParamsCheckResultList(AaListParamsCheckResult aaListParamsCheckResult) {
        ArrayList<AaListParamsCheckResultVo> vos = new ArrayList<>();
        try {
            List<AaListParamsCheckResult> list = aaListParamsHistoryCheckResultMapper.selectAaListParamsCheckResultList(aaListParamsCheckResult);
            list.forEach(item -> vos.add(new AaListParamsCheckResultVo(item)));
            return vos;
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("查询数据库发生异常，请联系管理员！");
        }
    }
}
