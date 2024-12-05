package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.AaListParamsIndex;
import com.qtech.im.aa.vo.AaListParamsIndexVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 14:38:02
 * desc   :
 */


public interface IAaListParamsIndexService {
    public List<AaListParamsIndexVo> selectAaListParamsIndexResultList(AaListParamsIndex aaListParamsIndex);

    public String updateTime();
}
