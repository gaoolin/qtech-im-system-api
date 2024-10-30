package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaListParamsCheckResultVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 09:37:30
 * desc   :
 */


public interface IAaListParamsLatestCheckResultService {
    public List<AaListParamsCheckResultVo> selectAaListParamsLatestCheckResultList(AaListParamsCheckResultVo aaListParamsCheckResultVo);

    public List<AaListParamsCheckResultVo> selectGroupNameList(AaListParamsCheckResultVo aaListParamsCheckResultVo);

    public List<AaListParamsCheckResultVo> selectFactoryNameList();
}
