package com.qtech.im.aa.controller;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.ImAaListParamsParsed;
import com.qtech.im.aa.service.IAaListParamsParsedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/07/05 15:49:03
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/parsed/detail")
public class AaListParamsParsedDetailController extends BaseController {

    @Autowired
    private IAaListParamsParsedDetailService aaListParamsParsedDetailService;

    @RequestMapping("/list")
    public TableDataInfo list(ImAaListParamsParsed aaListParamsParsed) {
        startPage();
        List<ImAaListParamsParsed> list = aaListParamsParsedDetailService.selectAaListParamsParsedDetailList(aaListParamsParsed);
        return getDataTable(list);
    }
}
