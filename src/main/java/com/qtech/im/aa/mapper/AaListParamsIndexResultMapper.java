package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsIndexVo;
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
    public List<AaListParamsIndexVo> list(AaListParamsIndexVo aaListParamsIndexVo);

    public LocalDateTime updateTime();
}
