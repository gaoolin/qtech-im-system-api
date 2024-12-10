package com.qtech.im.qcp.service;


import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.qcp.domain.QcpParamsDetail;
import com.qtech.im.qcp.domain.QcpParams;
import com.qtech.im.qcp.vo.QcpParamsDetailVo;
import com.qtech.im.qcp.vo.QcpParamsVo;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:51:55
 * desc   :
 */


public interface IQcpParamsService {

    public QtechImVoUtil.QtechImVos<QcpParamsVo> selectQcpParamsOverviewList(QcpParams qcpParams);

    public QtechImVoUtil.QtechImVos<QcpParamsDetailVo> selectQcpParamsList(QcpParamsDetail qcpParamsDetail);

    public boolean checkIotStatus();

    public String getMaxTime();
}
