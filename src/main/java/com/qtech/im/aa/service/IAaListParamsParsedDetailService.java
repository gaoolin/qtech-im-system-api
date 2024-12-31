package com.qtech.im.aa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qtech.im.aa.domain.parsed.AaListParamsParsed;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 15:57:27
 * desc   :
 */


public interface IAaListParamsParsedDetailService extends IService<AaListParamsParsed> {
    public List<AaListParamsParsed> selectParsedDetailList(AaListParamsParsed aaListParamsParsed);
}
