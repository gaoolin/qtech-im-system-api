package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaListParamsStdModelInfoVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/02 14:00:09
 * desc   :
 */


public interface IAaListParamsStdModelInfoService {
    public List<AaListParamsStdModelInfoVo> selectAaListParamsStdModelInfoList(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public AaListParamsStdModelInfoVo selectOneAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public int insertAaListParamsStdModelInfo(Object entity);

    public int updateAaListParamsStdModelInfo(AaListParamsStdModelInfoVo aaListParamsStdModelInfoVo);

    public int deleteAaListParamsStdModelInfoByIds(Long[] list);
}
