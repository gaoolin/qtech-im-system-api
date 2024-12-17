package com.qtech.im.aa.mapper.statistics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qtech.im.aa.vo.statistics.AaListParamsPercentageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/13 10:16:04
 * desc   :
 */

@Mapper
public interface AaListParamsPercentageMapper extends BaseMapper<AaListParamsPercentageVo> {
    public List<AaListParamsPercentageVo> selectAaParamsPercentageList(AaListParamsPercentageVo aaListParamsPercentageVo);
}
