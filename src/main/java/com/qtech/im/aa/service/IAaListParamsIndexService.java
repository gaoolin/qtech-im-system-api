package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.statistics.AaListParamsIndex;
import com.qtech.im.aa.vo.AaListParamsIndexVo;
import com.qtech.im.common.util.QtechImVoUtil;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 14:38:02
 * desc   :
 */


public interface IAaListParamsIndexService {
    public QtechImVoUtil.QtechImVos<AaListParamsIndexVo> selectAaListParamsIndexResultList(AaListParamsIndex aaListParamsIndex);

    public String updateTime();
}
