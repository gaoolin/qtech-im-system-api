package com.qtech.im.eqn.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.eqn.domain.ImEqsNetworkingAndRemoteInfoVo;
import com.qtech.im.eqn.service.IEqNetworkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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
    public TableDataInfo list(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        startPage();
        List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.selectEqNetworkingList(imEqsNetworkingAndRemoteInfoVo);
        return getDataTable(list);
    }

    @RequestMapping(value = "factoryNames")
    public R<List<ImEqsNetworkingAndRemoteInfoVo>> getFactoryNames() {
        List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.getFactoryNames();
        return R.ok(list);
    }

    @RequestMapping(value = "workShopNames")
    public R<List<ImEqsNetworkingAndRemoteInfoVo>> getWorkshopNames(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.getWorkshopNames(imEqsNetworkingAndRemoteInfoVo);
        return R.ok(list);
    }

    @RequestMapping(value = "offlineEqs", method = RequestMethod.GET)
    public TableDataInfo offlineEqs(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        startPage();
        List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.selectEqNetworkingOfflineList(imEqsNetworkingAndRemoteInfoVo);
        return getDataTable(list);
    }

    @Log(title = "设备联网明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        if (Optional.ofNullable(imEqsNetworkingAndRemoteInfoVo.getLabel()).isPresent() && imEqsNetworkingAndRemoteInfoVo.getLabel() == 1) {
            ImEqsNetworkingAndRemoteInfoVo eqNetworking = new ImEqsNetworkingAndRemoteInfoVo();
            BeanUtils.copyProperties(imEqsNetworkingAndRemoteInfoVo, eqNetworking);
            List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.selectEqNetworkingOfflineList(eqNetworking);
            ExcelUtil<ImEqsNetworkingAndRemoteInfoVo> util = new ExcelUtil<ImEqsNetworkingAndRemoteInfoVo>(ImEqsNetworkingAndRemoteInfoVo.class);
            util.exportExcel(response, list, "设备未联网明细");
        } else {
            List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.selectEqNetworkingList(imEqsNetworkingAndRemoteInfoVo);
            ExcelUtil<ImEqsNetworkingAndRemoteInfoVo> util = new ExcelUtil<ImEqsNetworkingAndRemoteInfoVo>(ImEqsNetworkingAndRemoteInfoVo.class);
            util.exportExcel(response, list, "设备联网明细");
        }
    }

    @RequestMapping(value = "/agg", method = RequestMethod.GET)
    public TableDataInfo selectEqNetworkingAgg(ImEqsNetworkingAndRemoteInfoVo imEqsNetworkingAndRemoteInfoVo) {
        List<ImEqsNetworkingAndRemoteInfoVo> list = eqNetworkingService.selectEqNetworkingAgg(imEqsNetworkingAndRemoteInfoVo);
        return getDataTable(list);
    }
}
