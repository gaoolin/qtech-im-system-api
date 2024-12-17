package com.qtech.im.aa.mapper.statistics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qtech.im.aa.vo.statistics.AaListParamsParticularsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/16 11:50:52
 * desc   :
 */

@Mapper
public interface AaListParamsParticularsMapper extends BaseMapper<AaListParamsParticularsVo> {
    public List<AaListParamsParticularsVo> getAll(AaListParamsParticularsVo aaListParamsParticularsVo);
}