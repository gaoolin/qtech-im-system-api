package com.qtech.im.aa.controller.statistics;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.statistics.AaListParamsIndex;
import com.qtech.im.aa.service.IAaListParamsIndexService;
import com.qtech.im.aa.vo.AaListParamsIndexVo;
import com.qtech.im.common.util.QtechImVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public TableDataInfo list(AaListParamsIndex aaListParamsIndex) {
        log.info("list params:{}", aaListParamsIndex);

        Map<String, Object> params = aaListParamsIndex.getParams();

        if (params != null) {
            boolean hasBeginTime = params.containsKey("beginTime");
            boolean hasEndTime = params.containsKey("endTime");
            if (hasBeginTime && hasEndTime) {
                startPage();
                QtechImVoUtil.QtechImVos<AaListParamsIndexVo> list = aaListParamsIndexService.selectAaListParamsIndexResultList(aaListParamsIndex);
                return QtechImVoUtil.getVoDataTable(list);
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
    public void export(HttpServletResponse response, AaListParamsIndex aaListParamsIndex) {
        QtechImVoUtil.QtechImVos<AaListParamsIndexVo> list = aaListParamsIndexService.selectAaListParamsIndexResultList(aaListParamsIndex);
        ExcelUtil<AaListParamsIndexVo> util = new ExcelUtil<AaListParamsIndexVo>(AaListParamsIndexVo.class);
        util.exportExcel(response, list.getData(), "AA参数点检概览");
    }
}