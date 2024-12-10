package com.qtech.im.wb.service;

import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.vo.WbOlpChkVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 14:24:45
 * desc   :
 */


public interface IWbOlpDetailService {

    public QtechImVoUtil.QtechImVos<WbOlpChkVo> getDetail(WbOlpChk wbOlpChk);
}
