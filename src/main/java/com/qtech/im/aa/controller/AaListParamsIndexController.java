package com.qtech.im.aa.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaListParamsIndexVo;
import com.qtech.im.aa.service.IAaListParamsIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/10/22 11:34:32
 * desc   :
 */

@Slf4j
@RestController
@RequestMapping("/aa/params/index")
public class AaListParamsIndexController extends BaseController {

    @Autowired
    private IAaListParamsIndexService aaListParamsIndexService;

    @GetMapping("/list")
    public TableDataInfo list(AaListParamsIndexVo aaListParamsIndexVo) {
        log.info("list params:{}", aaListParamsIndexVo);

        Map<String, Object> params = aaListParamsIndexVo.getParams();

        if (params != null) {
            boolean hasBeginTime = params.containsKey("beginTime");
            boolean hasEndTime = params.containsKey("endTime");
            if (hasBeginTime && hasEndTime) {
                startPage();
                List<AaListParamsIndexVo> list = aaListParamsIndexService.selectAaListParamsIndexResultList(aaListParamsIndexVo);
                return getDataTable(list);
            }
        }
        return null;
    }

    @GetMapping("/updateTime")
    public AjaxResult updateTime() {
        return success(aaListParamsIndexService.updateTime());
    }

    @Log(title = "AA参数点检概览导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AaListParamsIndexVo aaListParamsIndexVo) {
        List<AaListParamsIndexVo> list = aaListParamsIndexService.selectAaListParamsIndexResultList(aaListParamsIndexVo);
        ExcelUtil<AaListParamsIndexVo> util = new ExcelUtil<AaListParamsIndexVo>(AaListParamsIndexVo.class);
        util.exportExcel(response, list, "AA参数点检概览");
    }
}