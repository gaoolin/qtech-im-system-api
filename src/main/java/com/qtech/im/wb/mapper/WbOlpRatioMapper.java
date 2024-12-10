package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpPercentage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/15 13:27:04
 * desc   :
 */

@Repository
public interface WbOlpRatioMapper {

    public List<WbOlpPercentage> selectWbOlpRatioList(WbOlpPercentage wbOlpPercentage);
}
