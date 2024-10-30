package com.qtech.im.aa.service;

import com.qtech.im.aa.domain.ImAaListParamsParsed;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 15:57:27
 * desc   :
 */


public interface IAaListParamsParsedDetailService {
    public List<ImAaListParamsParsed> selectAaListParamsParsedDetailList(ImAaListParamsParsed aaListParamsParsed);
}
