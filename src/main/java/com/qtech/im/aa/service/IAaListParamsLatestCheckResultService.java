package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 09:37:30
 * desc   :
 */


public interface IAaListParamsLatestCheckResultService {
    public List<AaListParamsCheckResultVo> selectAaListParamsLatestCheckResultList(AaListParamsCheckResult aaListParamsCheckResult);

    public List<AaListParamsCheckResultVo> selectGroupNameList(AaListParamsCheckResult aaListParamsCheckResult);

    public List<AaListParamsCheckResultVo> selectFactoryNameList();
}
