package com.qtech.im.aa.service;


import com.qtech.im.aa.domain.AaListParamsCheckResult;
import com.qtech.im.aa.vo.AaListParamsCheckResultVo;
import com.qtech.im.common.util.QtechImVoUtil;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:57:52
 * desc   :
 */

public interface IAaListParamsHistoryCheckResultService {

    public QtechImVoUtil.QtechImVos<AaListParamsCheckResultVo> selectAaListParamsCheckResultList(AaListParamsCheckResult aaListParamsCheckResult);
}
