package com.qtech.im.wb.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.common.util.QtechImVoUtil;
import com.qtech.im.eqn.domain.ImEqsNetAndRemoteInfo;
import com.qtech.im.wb.domain.WbOlpChk;
import com.qtech.im.wb.service.IEmsEqInfoService;
import com.qtech.im.wb.service.IWbOlpChkService;
import com.qtech.im.wb.vo.WbOlpChkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/11/09 15:35:11
 * desc   :
 */

@RestController
@RequestMapping("/wb/olp/chk")
public class WbOlpChkController extends BaseController {

    @Autowired
    IEmsEqInfoService emsEqInfoService;

    @Autowired
    IWbOlpChkService wbOlpChkService;

    @GetMapping("/list/eqInfo")
    public TableDataInfo listEqInfo(WbOlpChk wbOlpChk) {
        ImEqsNetAndRemoteInfo imEqsNet = new ImEqsNetAndRemoteInfo();
        imEqsNet.setFactoryName(wbOlpChk.getFactoryName());
        imEqsNet.setGroupName(wbOlpChk.getGroupName());
        imEqsNet.setEqId(wbOlpChk.getEqId());
        imEqsNet.setMcId(wbOlpChk.getMcId());
        imEqsNet.setProdType(wbOlpChk.getProdType());
        imEqsNet.setSimId(wbOlpChk.getSimId());

        // 通过ThreadLocal传递参数
        startPage();
        List<ImEqsNetAndRemoteInfo> qtechImCommonInfos = emsEqInfoService.selectEmsEqInfoList(imEqsNet);

        return getDataTable(qtechImCommonInfos);
    }

    @GetMapping("/list/wbOlp")
    public TableDataInfo listWbOlp(WbOlpChk wbOlpChk) {
        /*会清除PageHelper在ThreadLocal的参数，需紧跟Mybatis查询方法*/
        startPage();  /* startPage后面返回的列表就应该是getDataTable，如果中间有其他类转换会导致分页数据丢失，详细查看getDataTable源码*/
        QtechImVoUtil.QtechImVos<WbOlpChkVo> wbOlpChkVos = wbOlpChkService.selectWbOlpChkList(wbOlpChk, 7);
        return QtechImVoUtil.getVoDataTable(wbOlpChkVos);
    }

    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/emsInfo/export")
    public void exportEmsInfo(HttpServletResponse response, ImEqsNetAndRemoteInfo imEqsNetAndRemoteInfo) {
        List<ImEqsNetAndRemoteInfo> list = emsEqInfoService.selectEmsEqInfoList(imEqsNetAndRemoteInfo);
        ExcelUtil<ImEqsNetAndRemoteInfo> util = new ExcelUtil<ImEqsNetAndRemoteInfo>(ImEqsNetAndRemoteInfo.class);
        util.exportExcel(response, list, "设备信息");
    }

    @Log(title = "比对结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void exportWbOlp(HttpServletResponse response, WbOlpChk wbOlpChk) {
        QtechImVoUtil.QtechImVos<WbOlpChkVo> wbOlpChkVos = wbOlpChkService.selectWbOlpChkList(wbOlpChk, 7);
        ExcelUtil<WbOlpChkVo> util = new ExcelUtil<>(WbOlpChkVo.class);
        util.exportExcel(response, wbOlpChkVos.getData(), "智慧打线图比对信息");
    }
}
