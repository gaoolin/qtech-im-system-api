package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.vo.WbOlpChkVo;
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

    public List<WbOlpChk> getDetail(WbOlpChk wbOlpChk);
}
