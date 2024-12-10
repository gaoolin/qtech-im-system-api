package com.qtech.im.qcp.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.qcp.domain.QcpParams;
import com.qtech.im.qcp.domain.QcpParamsDetail;
import com.qtech.im.qcp.service.IQcpParamsService;
import com.qtech.im.qcp.vo.QcpParamsDetailVo;
import com.qtech.im.qcp.vo.QcpParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/19 08:48:02
 * desc   :
 */

@RestController
@RequestMapping("/qcp/params")
public class QcpParamsController extends BaseController {

    @Autowired
    private IQcpParamsService qcpParamsService;

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public TableDataInfo list(QcpParams qcpParams) {
        QtechImVoUtil.QtechImVos<QcpParamsVo> list = qcpParamsService.selectQcpParamsOverviewList(qcpParams);
        return QtechImVoUtil.getVoDataTable(list);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public TableDataInfo listQcpParams(QcpParamsDetail qcpParamsDetail) {
        startPage();
        QtechImVoUtil.QtechImVos<QcpParamsDetailVo> qcpParamsDetails = qcpParamsService.selectQcpParamsList(qcpParamsDetail);
        return QtechImVoUtil.getVoDataTable(qcpParamsDetails);
    }

    @RequestMapping(value = "/maxTime", method = RequestMethod.GET)
    public R<String> getMaxTime() {
        String updateDt = qcpParamsService.getMaxTime();
        return R.ok(updateDt);
    }

    @RequestMapping(value = "/checkIotStatus", method = RequestMethod.GET)
    public AjaxResult checkIotStatus() {
        boolean b = qcpParamsService.checkIotStatus();
        return AjaxResult.success(null, b);
    }

    @Log(title = "qcp概览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcpParams qcpParams) {
        QtechImVoUtil.QtechImVos<QcpParamsVo> list = qcpParamsService.selectQcpParamsOverviewList(qcpParams);
        ExcelUtil<QcpParamsVo> util = new ExcelUtil<QcpParamsVo>(QcpParamsVo.class);
        util.exportExcel(response, list.getData(), "qcp概览");
    }

    @Log(title = "qcp明细", businessType = BusinessType.EXPORT)
    @PostMapping("/detail/export")
    public void export(HttpServletResponse response, QcpParamsDetail qcpParamsDetail) {
        QtechImVoUtil.QtechImVos<QcpParamsDetailVo> list = qcpParamsService.selectQcpParamsList(qcpParamsDetail);
        ExcelUtil<QcpParamsDetailVo> util = new ExcelUtil<QcpParamsDetailVo>(QcpParamsDetailVo.class);
        util.exportExcel(response, list.getData(), "qcp明细");
    }
}
