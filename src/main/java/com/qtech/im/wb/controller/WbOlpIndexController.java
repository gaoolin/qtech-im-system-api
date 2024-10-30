package com.qtech.im.wb.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.wb.domain.WbOlpIndexVo;
import com.qtech.im.wb.domain.WbOlpTrendingVo;
import com.qtech.im.wb.service.IWbOlpIndexService;
import com.qtech.im.wb.service.IWbOlpStdModelInfoService;
import com.qtech.im.wb.vo.WbOlpChkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    IWbOlpStdModelInfoService wbOlpStdModelInfoService;

    @Autowired
    IWbOlpIndexService wbOlpIndexService;


    @GetMapping(value = "/overview")
    public AjaxResult IndexWbOlpOverview() {

        WbOlpIndexVo wbOlpIndexVo = new WbOlpIndexVo();

        Long modelsTtlCnt = wbOlpStdModelInfoService.getModelsTtlCnt();
        Long modelAvgCnt = wbOlpStdModelInfoService.getModelAvgCnt();

        wbOlpIndexVo.setWbOlpStdModelsTtlCnt(modelsTtlCnt);
        wbOlpIndexVo.setWbOlpStdModelAvgCnt(modelAvgCnt);

        return AjaxResult.success(wbOlpIndexVo);
    }

    @GetMapping(value = "/trending")
    public TableDataInfo IndexWbOlpTrending() {
        List<WbOlpTrendingVo> wbOlpTrending = wbOlpStdModelInfoService.getWbOlpTrending();
        return getDataTable(wbOlpTrending);
    }


    @RequestMapping(value = "/factoryNames", method = RequestMethod.GET)
    public R<List<WbOlpChkVo>> getFactoryNames() {
        List<WbOlpChkVo> list = wbOlpIndexService.selectFactoryNameList();
        return R.ok(list);
    }

    @RequestMapping(value = "/workShopNames", method = RequestMethod.GET)
    public R getGroupNames(WbOlpChkVo wbOlpChkVo) {
        List<WbOlpChkVo> list = wbOlpIndexService.selectWorkShopNameList(wbOlpChkVo);
        return R.ok(list);
    }
}
