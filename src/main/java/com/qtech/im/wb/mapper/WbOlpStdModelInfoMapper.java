package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpStdModInfo;
import com.qtech.im.wb.domain.WbOlpTrendingVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/05 15:36:23
 * desc   :
 */

@Repository
public interface WbOlpStdModelInfoMapper {
    WbOlpStdModInfo selectWbOlpStdModInfoBySid(Long sid);

    List<WbOlpStdModInfo> selectWbOlpStdModInfoList(WbOlpStdModInfo wbOlpStdModInfo);

    int insertWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);

    int updateWbOlpStdModInfo(WbOlpStdModInfo wbOlpStdModInfo);

    int deleteWbOlpStdModInfoBySid(Long sid);

    int deleteWbOlpStdModInfoBySids(Long[] sids);

    long getModelAvgCnt();

    long getModelsTtlCnt();

    List<WbOlpTrendingVo> getWbOlpTrending();
}
