package com.qtech.im.wb.service;

import com.qtech.im.wb.vo.WbOlpChkVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 16:31:50
 * desc   :
 */


public interface IWbOlpChkService {

    List<WbOlpChkVo> selectWbOlpChkList(WbOlpChkVo vo, int daysFilter);
}
