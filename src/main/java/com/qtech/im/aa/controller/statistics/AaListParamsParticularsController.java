package com.qtech.im.aa.controller.statistics;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.service.IAaListParamsParticularsService;
import com.qtech.im.aa.vo.statistics.AaListParamsParticularsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/16 11:40:28
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/particulars")
public class AaListParamsParticularsController extends BaseController {
    private final IAaListParamsParticularsService aaListParamsParticularsService;

    @Autowired
    public AaListParamsParticularsController(IAaListParamsParticularsService aaListParamsParticularsService) {
        this.aaListParamsParticularsService = aaListParamsParticularsService;
    }

    @GetMapping("/list")
    public TableDataInfo list(AaListParamsParticularsVo aaListParamsParticularsVo) {
        startPage();
        List<AaListParamsParticularsVo> list = aaListParamsParticularsService.selectAaParamsParticalursList(aaListParamsParticularsVo);
        return getDataTable(list);
    }
}
