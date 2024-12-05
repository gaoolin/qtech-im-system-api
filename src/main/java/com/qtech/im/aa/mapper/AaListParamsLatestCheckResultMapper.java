package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.AaListParamsCheckResult;
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
    public List<AaListParamsCheckResult> selectAaListParamsLatestCheckResultList(AaListParamsCheckResult aaListParamsCheckResult);

    public List<AaListParamsCheckResult> selectGroupNameList(AaListParamsCheckResult aaListParamsCheckResult);

    public List<AaListParamsCheckResult> selectFactoryNames();
}
