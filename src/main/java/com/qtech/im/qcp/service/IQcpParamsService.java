package com.qtech.im.qcp.service;


import com.qtech.im.qcp.domain.QcpParamsDetailVo;
import com.qtech.im.qcp.domain.QcpParamsVo;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:51:55
 * desc   :
 */


public interface IQcpParamsService {

    public List<QcpParamsVo> selectQcpParamsOverviewList(QcpParamsVo qcpParamsVo);

    public List<QcpParamsDetailVo> selectQcpParamsList(QcpParamsDetailVo qcpParamsDetailVo);

    public boolean checkIotStatus();

    public String getMaxTime();
}
