package com.qtech.im.wb.service;

import com.qtech.im.wb.domain.WbOlpParticularsVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 14:24:45
 * desc   :
 */


public interface IWbOlpDetailService {

    public List<WbOlpParticularsVo> getDetail(WbOlpParticularsVo wbOlpParticularsVo);
}
