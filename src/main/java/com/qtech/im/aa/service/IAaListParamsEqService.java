package com.qtech.im.aa.service;


import com.qtech.im.aa.domain.AaListParamsEq;
import com.qtech.im.aa.vo.AaListParamsEqVo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.vo.WbOlpChkVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:05:47
 * desc   :
 */


public interface IAaListParamsEqService {
    public QtechImVoUtil.QtechImVos<AaListParamsEqVo> selectAaListParamsEqList(AaListParamsEq aaListParamsEq);

    public Boolean isExist(AaListParamsEq aaListParamsEq);

    public AaListParamsEq selectAaListParamsEqById(String id);

    public int editAaListParamsIgnoreEq(AaListParamsEq aaListParamsEq);

    public int insertAaListParamsIgnoreEq(AaListParamsEq aaListParamsEq);

    public void cleanupDayShiftIgnores(); // 实现清理白班的忽略状态

    public void cleanupNightShiftIgnores(); // 实现清理夜班的忽略状态
    public void cleanupIgnores(); // 这里根据你存储`simId`的策略清理Redis中的忽略键

    public int editAaListParamsEq(AaListParamsEq aaListParamsEq);
}
