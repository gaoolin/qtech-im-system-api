package com.qtech.im.wb.service;


import com.qtech.im.wb.domain.WbOlpPercentageVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 08:33:50
 * desc   :
 */


public interface IWbOlpRatioService {

    public List<WbOlpPercentageVo> getRatio(WbOlpPercentageVo wbOlpPercentageVo);
}
