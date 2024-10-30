package com.qtech.im.qcp.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.qcp.domain.QcpParamsDetailVo;
import com.qtech.im.qcp.domain.QcpParamsVo;
import com.qtech.im.qcp.service.IQcpParamsService;
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
 * date   :  2024/01/19 08:48:02
 * desc   :
 */

@RestController
@RequestMapping("/qcp/params")
public class QcpParamsController extends BaseController {

    @Autowired
    private IQcpParamsService qcpParamsService;

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public TableDataInfo list(QcpParamsVo qcpParamsVo) {
        List<QcpParamsVo> qcpParamsVos = qcpParamsService.selectQcpParamsOverviewList(qcpParamsVo);
        return getDataTable(qcpParamsVos);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public TableDataInfo listQcpParams(QcpParamsDetailVo qcpParamsDetailVo) {
        startPage();
        List<QcpParamsDetailVo> qcpParamsDetailVos = qcpParamsService.selectQcpParamsList(qcpParamsDetailVo);
        return getDataTable(qcpParamsDetailVos);
    }

    @RequestMapping(value = "/maxTime", method = RequestMethod.GET)
    public R<String> getMaxTime() {
        String updateDt = qcpParamsService.getMaxTime();
        return R.ok(updateDt);
    }

    @Log(title = "qcp概览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcpParamsVo qcpParamsVo)
    {
        List<QcpParamsVo> qcpParamsVos = qcpParamsService.selectQcpParamsOverviewList(qcpParamsVo);
        ExcelUtil<QcpParamsVo> util = new ExcelUtil<QcpParamsVo>(QcpParamsVo.class);
        util.exportExcel(response, qcpParamsVos, "qcp概览");
    }

    @Log(title = "qcp明细", businessType = BusinessType.EXPORT)
    @PostMapping("/detail/export")
    public void export(HttpServletResponse response, QcpParamsDetailVo qcpParamsDetailVo) {
        List<QcpParamsDetailVo> qcpParamsDetailVos = qcpParamsService.selectQcpParamsList(qcpParamsDetailVo);
        ExcelUtil<QcpParamsDetailVo> util = new ExcelUtil<QcpParamsDetailVo>(QcpParamsDetailVo.class);
        util.exportExcel(response, qcpParamsDetailVos, "qcp明细");
    }
}
