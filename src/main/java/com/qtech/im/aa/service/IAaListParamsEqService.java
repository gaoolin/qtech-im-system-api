package com.qtech.im.aa.service;


import com.qtech.im.aa.domain.AaListParamsEqVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/19 10:05:47
 * desc   :
 */


public interface IAaListParamsEqService {
    public List<AaListParamsEqVo> selectAaListParamsEqList(AaListParamsEqVo aaListParamsEqVo);

    public AaListParamsEqVo selectOneAaListParamsEq(AaListParamsEqVo aaListParamsEqVo);

    public Boolean isExist(AaListParamsEqVo aaListParamsEqVo);

    public AaListParamsEqVo selectAaListParamsEqById(String id);

    public int editAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo);

    public int insertAaListParamsIgnoreEq(AaListParamsEqVo aaListParamsEqVo);

    public void cleanupDayShiftIgnores(); // 实现清理白班的忽略状态

    public void cleanupNightShiftIgnores(); // 实现清理夜班的忽略状态
    public void cleanupIgnores(); // 这里根据你存储`simId`的策略清理Redis中的忽略键

    public int editAaListParamsEq(AaListParamsEqVo aaListParamsEqVo);
}
