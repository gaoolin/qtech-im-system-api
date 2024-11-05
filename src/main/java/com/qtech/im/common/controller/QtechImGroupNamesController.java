package com.qtech.im.common.controller;

import com.qtech.framework.web.domain.R;
import com.qtech.im.common.domain.ImReportBaseInfo;
import com.qtech.im.common.service.IQtechImGroupNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/24 13:47:52
 * desc   :
 */

@RestController
@RequestMapping("/qtech/im/groupNames")
public class QtechImGroupNamesController {

    @Autowired
    private IQtechImGroupNamesService qtechImGroupNamesService;

    @GetMapping("/wb/olp")
    public R<List<ImReportBaseInfo>> listWbOlpGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImGroupNamesService.getWbOlpGroupNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到车间信息");
        }
        return R.ok(list);
    }

    @GetMapping("/wb/olp/latest")
    public R<List<ImReportBaseInfo>> listWbOlpLatestGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImGroupNamesService.getWbOlpLatestGroupNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到车间信息");
        }
        return R.ok(list);
    }

    @GetMapping("/aa/history")
    public R<List<ImReportBaseInfo>> listHistoryGroupNames(ImReportBaseInfo imReportBaseInfo) {
        Map<String, Object> params = imReportBaseInfo.getParams();

        if (params == null) {
            return R.fail("请求参数中未携带任何参数");
        }

        boolean hasBeginTime = params.containsKey("beginTime");
        boolean hasEndTime = params.containsKey("endTime");

        if (hasBeginTime && hasEndTime) {
            List<ImReportBaseInfo> list = qtechImGroupNamesService.getHistoryGroupNames(imReportBaseInfo);
            if (list.isEmpty()) {
                return R.fail("未查询到车间信息");
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
    public R<List<ImReportBaseInfo>> listLatestGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImGroupNamesService.getLatestGroupNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到车间信息");
        }
        return R.ok(list);
    }

    @GetMapping("/wire")
    public R<List<ImReportBaseInfo>> listWireGroupNames(ImReportBaseInfo imReportBaseInfo) {
        List<ImReportBaseInfo> list = qtechImGroupNamesService.getWireGroupNames(imReportBaseInfo);
        if (list.isEmpty()) {
            return R.fail("未查询到线体信息");
        }
        return R.ok(list);
    }
}
