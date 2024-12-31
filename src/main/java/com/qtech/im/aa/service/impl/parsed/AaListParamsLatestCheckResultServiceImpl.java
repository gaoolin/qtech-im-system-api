package com.qtech.im.aa.service.impl.parsed;

import com.github.pagehelper.PageInfo;
import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.aa.domain.parsed.AaListParamsCheckResult;
import com.qtech.im.aa.mapper.parsed.AaListParamsLatestCheckResultMapper;
import com.qtech.im.aa.service.IAaListParamsLatestCheckResultService;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import com.qtech.im.common.util.QtechImVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QtechImVoUtil.QtechImVos<AaListParamsCheckResultVo> selectAaListParamsLatestCheckResultList(AaListParamsCheckResult aaListParamsCheckResult) {
        try {
            List<AaListParamsCheckResult> list = aaListParamsLatestCheckResultMapper.selectAaListParamsLatestCheckResultList(aaListParamsCheckResult);
            long total = new PageInfo(list).getTotal();
            List<AaListParamsCheckResultVo> vos = list.stream().map(AaListParamsCheckResultVo::new).collect(Collectors.toList());
            return new QtechImVoUtil.QtechImVos<>(vos, total);
        } catch (Exception e) {
            log.error("查询数据库发生异常，请联系管理员！\n{}", e.getMessage());
            throw new RuntimeException("查询数据库发生异常，请联系管理员！");
        }
    }
}
