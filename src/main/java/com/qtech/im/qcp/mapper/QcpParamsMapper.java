package com.qtech.im.qcp.mapper;

import com.qtech.im.qcp.domain.QcpParamsDetailVo;
import com.qtech.im.qcp.domain.QcpParamsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:50:51
 * desc   :
 */

@Repository
public interface QcpParamsMapper {

    public List<QcpParamsVo> selectQcpParamsOverviewList(QcpParamsVo qcpParams);

    public List<QcpParamsDetailVo> selectQcpParamsList(QcpParamsDetailVo qcpParamsDetailVo);

    public String getMaxTime();

    public boolean checkIotStatus();
}
