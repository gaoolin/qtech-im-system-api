package com.qtech.im.eqn.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.domain.ImEqsNetCnt;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
import com.qtech.im.eqn.service.IEqNetworkingService;
import com.qtech.im.eqn.vo.ImEqsNetAndRemoteInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:43:28
 * desc   :
 */

@RestController
@RequestMapping("/eqn/status")
public class EqNetworkingController extends BaseController {

    @Autowired
    private IEqNetworkingService eqNetworkingService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public TableDataInfo list(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        startPage();
        QtechImVoUtil.QtechImVos<ImEqsNetAndRemoteInfoVo> list = eqNetworkingService.list(imEqsNetAndRemoteInfo);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @RequestMapping(value = "offlineEqs", method = RequestMethod.GET)
    public TableDataInfo offlineEqs(ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        startPage();
        List<ImEqsNetAndRemoteInfo> list = eqNetworkingService.offlineList(imEqsNetAndRemoteInfo);
        return getDataTable(list);
    }

    @Log(title = "设备联网明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        QtechImVoUtil.QtechImVos<ImEqsNetAndRemoteInfoVo> list = eqNetworkingService.list(imEqsNetAndRemoteInfo);
        ExcelUtil<ImEqsNetAndRemoteInfoVo> util = new ExcelUtil<ImEqsNetAndRemoteInfoVo>(ImEqsNetAndRemoteInfoVo.class);
        util.exportExcel(response, list.getData(), "设备联网明细");
    }

    @RequestMapping(value = "/agg", method = RequestMethod.GET)
    public TableDataInfo selectEqNetworkingAgg(ImEqsNetCnt imEqsNetAndRemoteInfo) {
        List<ImEqsNetCnt> list = eqNetworkingService.agg(imEqsNetAndRemoteInfo);
        return getDataTable(list);
    }
}
