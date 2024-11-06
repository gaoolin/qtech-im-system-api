package com.qtech.im.common.controller;

import com.qtech.framework.web.domain.R;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.service.IQtechImEqsInfoService;
import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 14:14:28
 * desc   :
 */

@RestController
@RequestMapping("/qtech/im/eqs")
public class QtechImEqsInfoController {
    @Autowired
    private IQtechImEqsInfoService qtechImEqsInfoService;

    @GetMapping("/aa/index")
    public R<List<ImEqsNetworkingAndRemoteInfoVo>> listEqsInfo(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        return R.ok(qtechImEqsInfoService.listEqsInfo(imEqsNetworkingAndRemoteInfoVo));
    }

    @GetMapping("/iot/status")
    public R<Boolean> iotStatus(ImReportBaseInfo imReportBaseInfo) {
        return R.ok(qtechImEqsInfoService.iotStatus(imReportBaseInfo));
    }

    @GetMapping("/iot/aa/status")
    public R<Boolean> iotAaStatus(ImReportBaseInfo imReportBaseInfo) {
        return R.ok(qtechImEqsInfoService.iotAaStatus(imReportBaseInfo));
    }
}
