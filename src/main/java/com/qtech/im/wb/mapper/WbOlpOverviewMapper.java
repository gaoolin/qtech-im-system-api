package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpOverview;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/04 15:24:56
 * desc   :
 */

@Repository
public interface WbOlpOverviewMapper {

    public List<WbOlpOverview> selectWbOlpOverviewList(WbOlpOverview wbOlpOverview);

    public String getUpdateTime();
}
