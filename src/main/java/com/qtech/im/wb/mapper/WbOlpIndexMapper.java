package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpTrending;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/01 12:20:27
 * desc   :
 */

@Repository
public interface WbOlpIndexMapper {

    List<WbOlpTrending> getWbOlpTrending();

    Long getModelAvgCnt();

    Long getModelsTtlCnt();
}
