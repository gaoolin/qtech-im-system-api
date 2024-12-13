package com.qtech.im.aa.controller.statistics;

import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.service.statistics.IAaListParamsPercentageService;
import com.qtech.im.aa.vo.statistics.AaListParamsPercentageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/12/13 10:11:21
 * desc   :
 */

@RestController
@RequestMapping("/aa/params/percentage")
public class AaListParamsPercentageController extends BaseController {

    private final IAaListParamsPercentageService aaListParamsPercentageService;

    @Autowired
    public AaListParamsPercentageController(IAaListParamsPercentageService aaListParamsPercentageService) {
        this.aaListParamsPercentageService = aaListParamsPercentageService;
    }

    @GetMapping("list")
    public TableDataInfo list(AaListParamsPercentageVo aaListParamsPercentage) {
        List<AaListParamsPercentageVo> list = aaListParamsPercentageService.selectAaParamsPercentageList(aaListParamsPercentage);
        return getDataTable(list);
    }
}
