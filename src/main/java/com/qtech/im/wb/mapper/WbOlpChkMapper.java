package com.qtech.im.wb.mapper;

import com.qtech.im.wb.domain.WbOlpChk;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 16:12:55
 * desc   :
 */

@Repository
public interface WbOlpChkMapper {

    public List<WbOlpChk> selectWbOlpChkList(@Param("vo") WbOlpChk vo, @Param("daysFilter") int daysFilter);
}
