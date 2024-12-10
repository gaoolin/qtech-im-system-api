package com.qtech.im.wb.service;

import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.vo.WbOlpChkVo;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 16:31:50
 * desc   :
 */


public interface IWbOlpChkService {

    QtechImVoUtil.QtechImVos<WbOlpChkVo> selectWbOlpChkList(WbOlpChk vo, int daysFilter);
}
