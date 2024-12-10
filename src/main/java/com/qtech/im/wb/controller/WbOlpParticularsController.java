package com.qtech.im.wb.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.service.IWbOlpDetailService;
import com.qtech.im.wb.vo.WbOlpChkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/01/16 14:30:14
 * desc   :
 */

@RestController
@RequestMapping("/wb/olp/particulars")
public class WbOlpParticularsController extends BaseController {

    @Autowired
    private IWbOlpDetailService wbOlpDetailService;

    @RequestMapping(value = "/detail")
    public TableDataInfo getDetail(WbOlpChk wbOlpChk) {
        startPage();
        QtechImVoUtil.QtechImVos<WbOlpChkVo> detail = wbOlpDetailService.getDetail(wbOlpChk);
        return QtechImVoUtil.getVoDataTable(detail);
    }

    @Log(title = "打线图机台比对明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WbOlpChk wbOlpChk) {
        QtechImVoUtil.QtechImVos<WbOlpChkVo> list = wbOlpDetailService.getDetail(wbOlpChk);
        ExcelUtil<WbOlpChkVo> util = new ExcelUtil<WbOlpChkVo>(WbOlpChkVo.class);
        util.exportExcel(response, list.getData(), "打线图机台比对明细");
    }
}
