package com.qtech.im.common.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.R;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.service.IQtechImFactoryNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 15:54:24
 * desc   :
 */

@RestController
@RequestMapping("/qtech/im/factoryNames")
public class QtechImFactoryNamesController extends BaseController {

    @Autowired
    private IQtechImFactoryNamesService qtechImFactoryInfoService;

    @GetMapping("/wb/olp")
    public R<List<ImReportBaseInfo>> listWbOlpFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getWbOlpFactoryNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }

    @GetMapping("/wb/olp/latest")
    public R<List<ImReportBaseInfo>> listWbOlpLatestFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getWbOlpLatestFactoryNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }

    @GetMapping("/eqn")
    public R<List<ImReportBaseInfo>> listEqnFactoryNames() {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getEqnFactoryNames();
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }

    @GetMapping("/qcp")
    public R<List<ImReportBaseInfo>> listQcpFactoryNames() {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getQcpFactoryNames();
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }

    @GetMapping("/aa/history")
    public R<List<ImReportBaseInfo>> listHistoryFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        Map<String, Object> params = imReportBaseInfo.getParams();

        if (params == null) {
            return R.fail("请求参数中未携带任何参数");
        }

        boolean hasBeginTime = params.containsKey("beginTime");
        boolean hasEndTime = params.containsKey("endTime");

        if (hasBeginTime && hasEndTime) {
            List<ImReportBaseInfo> list = qtechImFactoryInfoService.getHistoryFactoryNames(imReportBaseInfo);
            if (list.isEmpty()) {
                return R.fail("未查询到厂区信息");
            }
            return R.ok(list);
        } else if (hasBeginTime) {
            return R.fail("请求参数中携带的开始时间为: " + params.get("beginTime") + ", 但未携带结束时间");
        } else if (hasEndTime) {
            return R.fail("请求参数中携带的结束时间为: " + params.get("endTime") + ", 但未携带开始时间");
        } else {
            return R.fail("请求参数中未携带开始时间和结束时间");
        }
    }

    @GetMapping("/aa/latest")
    public R<List<ImReportBaseInfo>> listLatestFactoryNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getLatestFactoryNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }

    @GetMapping("/wire")
    public R<List<ImReportBaseInfo>> listWireNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImFactoryInfoService.getWireFactoryNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到厂区信息");
        }
        return R.ok(list);
    }
}
