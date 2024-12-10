package com.qtech.im.wb.service;


import com.qtech.im.wb.domain.WbOlpStdModInfo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/05 15:30:58
 * desc   :
 */


public interface IWbOlpStdModelInfoService {
    public WbOlpStdModInfo selectWbOlpStdModInfoBySid(Long sid);
    public List<WbOlpStdModInfo> selectWbOlpStdModInfoList(WbOlpStdModInfo wbOlpStdModInfo);
    public int insertWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);
    public int updateWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);
    public int deleteWbOlpStdModInfoBySid(Long sid);
    public int deleteWbOlpStdModInfoBySids(Long[] sids);
}
