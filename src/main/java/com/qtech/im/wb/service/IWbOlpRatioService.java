package com.qtech.im.wb.service;


import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpPercentage;
import com.qtech.im.wb.vo.WbOlpChkVo;
import com.qtech.im.wb.vo.WbOlpPercentageVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 08:33:50
 * desc   :
 */


public interface IWbOlpRatioService {

    public QtechImVoUtil.QtechImVos<WbOlpPercentageVo> getRatio(WbOlpPercentage wbOlpPercentage);
}
