package com.qtech.im.aa.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.AaListParamsCheckResultVo;
import com.qtech.im.aa.mapper.AaListParamsLatestCheckResultMapper;
import com.qtech.im.aa.service.IAaListParamsLatestCheckResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 09:38:15
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class AaListParamsLatestCheckResultServiceImpl implements IAaListParamsLatestCheckResultService {

    @Autowired
    private AaListParamsLatestCheckResultMapper aaListParamsLatestCheckResultMapper;

    @Override
    public List<AaListParamsCheckResultVo> selectAaListParamsLatestCheckResultList(AaListParamsCheckResultVo aaListParamsCheckResultVo) {
        List<AaListParamsCheckResultVo> list = null;
        try {
            list = aaListParamsLatestCheckResultMapper.selectAaListParamsLatestCheckResultList(aaListParamsCheckResultVo);
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("查询数据库发生异常，请联系管理员！");
        }
        return list;
    }

    @Override
    public List<AaListParamsCheckResultVo> selectGroupNameList(AaListParamsCheckResultVo aaListParamsCheckResultVo) {
        List<AaListParamsCheckResultVo> list = null;
        try {
            list = aaListParamsLatestCheckResultMapper.selectGroupNameList(aaListParamsCheckResultVo);
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("查询数据库发生异常，请联系管理员！");
        }
        return list;
    }

    @Override
    public List<AaListParamsCheckResultVo> selectFactoryNameList() {
        List<AaListParamsCheckResultVo> list = null;
        try {
            list = aaListParamsLatestCheckResultMapper.selectFactoryNames();
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("查询数据库发生异常，请联系管理员！");
        }
        return list;
    }
}
