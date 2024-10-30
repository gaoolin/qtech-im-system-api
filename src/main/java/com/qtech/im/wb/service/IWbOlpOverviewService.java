package com.qtech.im.wb.service;


import com.qtech.im.wb.domain.WbOlpOverview;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/05 08:52:19
 * desc   :
 */


public interface IWbOlpOverviewService {

    public List<WbOlpOverview> selectWbOlpOverviewList(WbOlpOverview wbOlpOverview);
    public String getUpdateTime();
}
