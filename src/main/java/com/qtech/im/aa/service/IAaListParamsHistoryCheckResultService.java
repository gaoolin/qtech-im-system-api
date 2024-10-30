package com.qtech.im.aa.service;


import com.qtech.im.aa.domain.AaListParamsCheckResultVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:57:52
 * desc   :
 */

public interface IAaListParamsHistoryCheckResultService {

    public List<AaListParamsCheckResultVo> selectAaListParamsCheckResultList(AaListParamsCheckResultVo aaListParamsCheckResultVo);
}
