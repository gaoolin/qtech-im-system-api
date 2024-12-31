package com.qtech.im.aa.mapper.statistics;

import com.qtech.im.aa.domain.statistics.AaListParamsIndex;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 17:21:05
 * desc   :
 */


@Mapper
public interface AaListParamsIndexResultMapper {
    public List<AaListParamsIndex> list(AaListParamsIndex aaListParamsIndex);

    public LocalDateTime updateTime();
}
