package com.qtech.im.aa.mapper;

import com.qtech.im.aa.domain.ImAaListParamsParsed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 15:50:46
 * desc   :
 */

@Mapper
public interface AaListParamsParsedDetailMapper  {

    public List<ImAaListParamsParsed> selectAaListParamsParsedDetailList(ImAaListParamsParsed aaListParamsParsed);
}
