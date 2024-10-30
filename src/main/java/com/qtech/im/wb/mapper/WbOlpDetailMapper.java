package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpParticularsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 14:08:12
 * desc   :
 */

@Mapper
public interface WbOlpDetailMapper {

    public List<WbOlpParticularsVo> getDetail(WbOlpParticularsVo wbOlpParticularsVo);
}
