package com.qtech.im.wire.controller;

import com.qtech.common.utils.poi.ExcelUtil;
import com.qtech.framework.aspectj.lang.annotation.Log;
import com.qtech.framework.aspectj.lang.enums.BusinessType;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.im.wire.domain.WireUsageStandard;
import com.qtech.im.wire.service.IWireUsageStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : gaozhilin
 * @project : qtech
 * @email : gaoolin@gmail.com
 * @date : 2023/03/30 10:05:09
 * @description : 金线模板批量导入
 */

@RestController
@RequestMapping("/biz/wire/standard")
public class WireUsageImportController {

    @Autowired
    private IWireUsageStandardService wireUsageStandardService;


    /**
     * 导入标准模板
     */
    // @RequiresPermissions("cob/wb:stdmodel:import")
    @PreAuthorize("@ss.hasPermi('biz/wire/standard:import')")
    @Log(title = "金线标准用量", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importStdMod(MultipartFile file) throws Exception
    {
        ExcelUtil<WireUsageStandard> util = new ExcelUtil<>(WireUsageStandard.class);
        List<WireUsageStandard> wireUsageStandards = util.importExcel(file.getInputStream());
        String countN = wireUsageStandardService.importWireUsageStandard(wireUsageStandards);
        String[] split = countN.split("-");
        int insert = Integer.parseInt(split[0]);
        int exist = Integer.parseInt(split[1]);


        return AjaxResult.success("共" + (insert + exist) +"条数据，已导入："  + insert + "条，未导入：" + exist + "条。");
    }

    /**
     * @description  模板下载
     * @param response
     * @return void
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {

        ExcelUtil<WireUsageStandard> util = new ExcelUtil<>(WireUsageStandard.class);
        util.importTemplateExcel(response, "金线标准用量表");
    }

}
