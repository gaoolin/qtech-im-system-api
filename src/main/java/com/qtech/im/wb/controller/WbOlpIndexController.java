package com.qtech.im.wb.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wb.domain.WbOlpTrending;
import com.qtech.im.wb.service.IWbOlpIndexService;
import com.qtech.im.wb.vo.WbOlpIndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2023/09/16 10:15:16
 * desc   :
 */

@RestController
@RequestMapping("/wb/olp/index")
public class WbOlpIndexController extends BaseController {

    @Autowired
    IWbOlpIndexService wbOlpIndexService;

    @GetMapping(value = "/overview")
    public AjaxResult IndexWbOlpOverview() {

        WbOlpIndexVo wbOlpIndexVo = new WbOlpIndexVo();

        Long modelsTtlCnt = wbOlpIndexService.getModelsTtlCnt();
        Long modelAvgCnt = wbOlpIndexService.getModelAvgCnt();

        wbOlpIndexVo.setWbOlpStdModelsTtlCnt(modelsTtlCnt);
        wbOlpIndexVo.setWbOlpStdModelAvgCnt(modelAvgCnt);

        return AjaxResult.success(wbOlpIndexVo);
    }

    @GetMapping(value = "/trending")
    public TableDataInfo IndexWbOlpTrending() {
        List<WbOlpTrending> wbOlpTrending = wbOlpIndexService.getWbOlpTrending();
        return getDataTable(wbOlpTrending);
    }

}
