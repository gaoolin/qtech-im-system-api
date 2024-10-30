package com.qtech.im.wb.service.impl;

import com.qtech.framework.aspectj.lang.annotation.DataSource;
import com.qtech.framework.aspectj.lang.enums.DataSourceType;
import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import com.qtech.im.wb.mapper.EmsEqInfoMapper;
import com.qtech.im.wb.service.IEmsEqInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 17:01:14
 * desc   :
 */

@Slf4j
@DataSource(DataSourceType.SECOND)
@Service
public class EmsEqInfoServiceImpl implements IEmsEqInfoService {

    @Autowired
    EmsEqInfoMapper emsEqInfoMapper;

    @Override
    public List<ImEqsNetworkingAndRemoteInfoVo> selectEmsEqInfoList(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        try {
            return emsEqInfoMapper.selectEmsEqInfoList(imEqsNetworkingAndRemoteInfoVo);
        } catch (Exception e) {
            log.error("查询emsEqInfo异常", e);
            throw new RuntimeException("查询数据库失败，请联系系统负责人！");
        }
    }
}
