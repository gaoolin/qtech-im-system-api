package com.qtech.im.aa.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtech.common.utils.DateUtils;
import com.qtech.common.utils.StringUtils;
import com.qtech.framework.web.controller.BaseController;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.framework.web.domain.R;
import com.qtech.framework.web.page.TableDataInfo;
import com.qtech.im.aa.domain.AaStdProgramManagementVo;
import com.qtech.im.aa.service.IAaS3Service;
import com.qtech.im.aa.service.IAaStdProgramManagementService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/02 09:02:21
 * desc   :
 */

@Slf4j
@RestController
@RequestMapping("/aa/program")
@Api(value = "S3 Controller" , tags = "S3 操作服务")
public class AaStdProgramManagementController extends BaseController {

    @Autowired
    private IAaS3Service aaS3Service;

    @Autowired
    private IAaStdProgramManagementService aaStdProgramManagementService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    public TableDataInfo search(AaStdProgramManagementVo aaStdProgramManagementVo) {
        startPage();
        List<AaStdProgramManagementVo> list = aaStdProgramManagementService.selectAaStdProgramManagementList(aaStdProgramManagementVo);
        return getDataTable(list);
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public AjaxResult add(@RequestBody AaStdProgramManagementVo aaStdProgramManagementVo) {

        String fileName = aaStdProgramManagementVo.getFileName();
        // 检查文件名是否符合要求
        String[] result = new String[0];
        if (fileName != null) {
            result = extractFileNameAndVersion(fileName);
        }
        if (result == null) {
            return warn("文件名不符合规范，请确保文件为压缩文件并带有版本号");
        }

        String prefix = result[0];
        String version = result[1];

        if (!(StringUtils.upperCase(fileName).endsWith(".ZIP") || StringUtils.upperCase(fileName).endsWith(".RAR") || StringUtils.upperCase(fileName).endsWith(".7Z"))) {
            return warn("文件类型不正确，请上传压缩包");
        }

        aaStdProgramManagementVo.setProdType(StringUtils.upperCase(prefix));
        aaStdProgramManagementVo.setFileName(fileName); // 添加文件前缀
        aaStdProgramManagementVo.setVersion(StringUtils.upperCase(version)); // 添加版本号
        aaStdProgramManagementVo.setStatus(0);
        aaStdProgramManagementVo.setDownloadCnt(0L);
        String nickName = getLoginUser().getUser().getNickName();
        aaStdProgramManagementVo.setCreateBy(nickName);

        int i = aaStdProgramManagementService.insertAaStdProgramManagement(aaStdProgramManagementVo);
        return success(i);
    }

    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody AaStdProgramManagementVo aaStdProgramManagementVo) {
        aaStdProgramManagementVo.setUpdateBy(getUsername());
        aaStdProgramManagementVo.setUpdateTime(DateUtils.getNowDate());
        return toAjax(aaStdProgramManagementService.updateAaStdProgramManagement(aaStdProgramManagementVo));
    }

    @GetMapping("/isExist")
    public R<Boolean> doCheckFileExist(@RequestParam("fileName") String fileName) {
        AaStdProgramManagementVo aaStdProgramManagementVo = new AaStdProgramManagementVo();
        String[] params = extractFileNameAndVersion(fileName);
        if (params != null) {
            String prodType = params[0];
            String version = params[1];
            aaStdProgramManagementVo.setProdType(prodType);
            aaStdProgramManagementVo.setVersion(version);
            aaStdProgramManagementVo.setFlag(0);
            List<AaStdProgramManagementVo> list = aaStdProgramManagementService.selectAaStdProgramManagementList(aaStdProgramManagementVo);
            if (!list.isEmpty()) {
                return R.ok(true);
            }
            return R.ok(false);
        }
        throw new RuntimeException("文件名不符合规范，请确保文件为压缩文件并带有版本号");
    }

    @RequestMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        AaStdProgramManagementVo aaStdProgramManagementVo = new AaStdProgramManagementVo();
        String originalFilename = file.getOriginalFilename();

        // 检查文件名是否符合要求
        String[] result = new String[0];
        if (originalFilename != null) {
            result = extractFileNameAndVersion(originalFilename);
        }
        if (result == null) {
            return warn("文件名不符合要求，请确保文件为压缩文件并带有版本号");
        }

        String prefix = result[0];
        String version = result[1];

        if (!(StringUtils.upperCase(originalFilename).endsWith(".ZIP") || StringUtils.upperCase(originalFilename).endsWith(".RAR") || StringUtils.upperCase(originalFilename).endsWith(".7Z"))) {
            return warn("文件类型不正确，请上传压缩包");
        }

        aaStdProgramManagementVo.setProdType(StringUtils.upperCase(prefix));
        aaStdProgramManagementVo.setSize(formatFileSize(file.getSize())); // 格式化文件大小
        aaStdProgramManagementVo.setFileName(originalFilename); // 添加文件前缀
        aaStdProgramManagementVo.setVersion(StringUtils.upperCase(version)); // 添加版本号
        aaStdProgramManagementVo.setStatus(1);
        aaStdProgramManagementVo.setDownloadCnt(0L);
        String nickName = getLoginUser().getUser().getNickName();
        Date getNowDate = DateUtils.getNowDate();
        aaStdProgramManagementVo.setCreateBy(nickName);
        aaStdProgramManagementVo.setCreateTime(getNowDate);

        String data = aaS3Service.uploadFile("aa-program-files" , file);

        aaStdProgramManagementService.insertAaStdProgramManagement(aaStdProgramManagementVo);
        return success(file.getOriginalFilename() + ":" + data);
    }

    private String[] extractFileNameAndVersion(String fileName) {
        // 提取文件名中的文件前缀和版本号
        int dashIndex = fileName.lastIndexOf('_');
        int dotIndex = fileName.lastIndexOf('.');

        if (dashIndex == -1 || dotIndex == -1 || dashIndex > dotIndex) {
            return null;
        }

        String prefix = fileName.substring(0, dashIndex);
        String version = fileName.substring(dashIndex + 1, dotIndex);
        String suffix = fileName.substring(dotIndex + 1);

        return new String[]{prefix, version, suffix};
    }

    private String formatFileSize(long size) {
        if (size < 1024) {
            return size + " Bytes";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f Kb" , (double) size / 1024);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f Mb" , (double) size / (1024 * 1024));
        } else {
            return String.format("%.2f Gb" , (double) size / (1024 * 1024 * 1024));
        }
    }

    @RequestMapping(value = "/download/getUrl" , method = RequestMethod.GET)
    public R<String> generatePresignedGetUrl(@RequestParam("fileName") String fileName) throws Exception {
        String aaProgramNameList = aaS3Service.listFiles("aa-program-files");
        log.info(">>>>> aaProgramNameList: " + aaProgramNameList);
        try {
            // 下载文件字节流
            String getUrl = aaS3Service.generatePresignedGetUrl("aa-program-files" , fileName);
            JsonNode jsonNode = objectMapper.readTree(getUrl);
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    // 对文件名进行URL编码，避免中文乱码
                    // String encodedFileName = URLEncoder.encode(fileName, "UTF-8");

                    AaStdProgramManagementVo aaStdProgramManagementVo = new AaStdProgramManagementVo();
                    aaStdProgramManagementVo.setFileName(fileName);
                    AaStdProgramManagementVo aaStdProgramManagementVoDb = aaStdProgramManagementService.selectOneAaStdProgramManagement(aaStdProgramManagementVo);
                    aaStdProgramManagementVoDb.setDownloadCnt(aaStdProgramManagementVoDb.getDownloadCnt() + 1L);
                    aaStdProgramManagementService.updateAaStdProgramManagement(aaStdProgramManagementVoDb);
                    return R.ok(jsonNode.get("data").asText());
                } else {
                    return R.fail(jsonNode.get("msg").asText());
                }
            }
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
        return R.fail("文件下载地址获取失败");
    }

    @RequestMapping(value = "/upload/putUrl" , method = RequestMethod.GET)
    public R<String> generatePresignedPutUrl(@RequestParam("fileName") String fileName) throws Exception {
        String putUrl = aaS3Service.generatePresignedPutUrl("aa-program-files" , fileName);
        JsonNode jsonNode = objectMapper.readTree(putUrl);
        if (jsonNode.has("code")) {
            if (jsonNode.get("code").asInt() == 200) {
                return R.ok(jsonNode.get("data").asText());
            } else {
                return R.fail(jsonNode.get("msg").asText());
            }
        }
        return R.fail("文件上传地址获取失败");
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    public AjaxResult deleteFile(@RequestParam("fileName") String fileName, Long id) throws Exception {
        String data = aaS3Service.deleteFile("aa-program-files" , fileName);
        aaStdProgramManagementService.deleteAaStdProgramManagementById(id);

        return success(fileName + ":" + data);
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public AjaxResult listFiles() throws Exception {
        String data = aaS3Service.listFiles("aa-program-files");
        return success(data);
    }

    @RequestMapping(value = "/metadata" , method = RequestMethod.GET)
    public AjaxResult getFileMetadata(@RequestParam("fileName") String fileName) throws Exception {
        String data = aaS3Service.getFileMetadata("aa-program-files" , fileName).toString();
        return success(data);
    }

    @RequestMapping(value = "/rename" , method = RequestMethod.GET)
    public AjaxResult rename(@RequestParam("fileName") String fileName, @RequestParam("newFileName") String newFileName) throws Exception {
        return aaS3Service.rename("aa-program-files" , fileName, newFileName);
    }
}
