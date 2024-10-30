package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsCheckResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/03 10:52:43
 * desc   :
 */

@Mapper
public interface AaListParamsHistoryCheckResultMapper {

    public List<AaListParamsCheckResultVo> selectAaListParamsCheckResultList(AaListParamsCheckResultVo aaListParamsCheckResultVo);
}
