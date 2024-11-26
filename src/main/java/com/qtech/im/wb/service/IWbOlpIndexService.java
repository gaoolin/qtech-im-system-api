package com.qtech.im.wb.service;

import com.qtech.im.wb.domain.WbOlpTrendingVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/01 12:13:41
 * desc   :
 */


public interface IWbOlpIndexService {

    public List<WbOlpTrendingVo> getWbOlpTrending();

    public Long getModelsTtlCnt();

    public Long getModelAvgCnt();
}
