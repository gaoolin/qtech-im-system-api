package com.qtech.im.wb.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wb.domain.WbOlpPercentageVo;
import com.qtech.im.wb.service.IWbOlpRatioService;
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
 * date   :  2024/01/15 11:54:01
 * desc   :
 */

@RestController
@RequestMapping("/wb/olp/percentage")
public class WbOlpPercentageController extends BaseController {

    @Autowired
    private IWbOlpRatioService wbOlpRatioService;

    @RequestMapping(value = "/ratio" , method = RequestMethod.GET)
    public TableDataInfo getRatio(WbOlpPercentageVo wbOlpPercentageVo) {
        startPage();
        List<WbOlpPercentageVo> ratio = wbOlpRatioService.getRatio(wbOlpPercentageVo);
        return getDataTable(ratio);
    }

    @Log(title = "打线图机台比对正确率" , businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WbOlpPercentageVo wbOlpPercentageVo) {
        List<WbOlpPercentageVo> list = wbOlpRatioService.getRatio(wbOlpPercentageVo);
        ExcelUtil<WbOlpPercentageVo> util = new ExcelUtil<WbOlpPercentageVo>(WbOlpPercentageVo.class);
        util.exportExcel(response, list, "打线图机台比对正确率");
    }
}
