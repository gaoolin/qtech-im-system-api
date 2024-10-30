package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsCheckResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/06/04 09:35:24
 * desc   :
 */

@Mapper
public interface AaListParamsLatestCheckResultMapper {
    public List<AaListParamsCheckResultVo> selectAaListParamsLatestCheckResultList(AaListParamsCheckResultVo aaListParamsCheckResultVo);

    public List<AaListParamsCheckResultVo> selectGroupNameList(AaListParamsCheckResultVo aaListParamsCheckResultVo);

    public List<AaListParamsCheckResultVo> selectFactoryNames();
}
