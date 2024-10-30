package com.qtech.im.aa.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtech.framework.web.domain.AjaxResult;
import com.qtech.im.aa.service.IAaS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/08/02 16:15:52
 * desc   :
 */

@Slf4j
@Service
public class AaStdS3ServiceImpl implements IAaS3Service {
    private static final String S3_BASE_URL = "http://10.170.6.40:31555/s3/files"; // 替换为实际S3服务的地址
    // private static final String S3_BASE_URL = "http://localhost:8081/s3/files"; // 替换为实际S3服务的地址
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AaStdS3ServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String uploadFile(String bucketName, MultipartFile file) throws Exception {
        String url = S3_BASE_URL + "/upload";

        // 创建一个 MultiValueMap 用于存储文件和其他表单字段
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("bucketName", bucketName);
        body.add("file", file.getResource()); // 使用 getResource() 将文件转换为合适的类型

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    return jsonNode.get("data").asText();
                } else {
                    throw new Exception(jsonNode.get("msg").asText());
                }
            }
        }
        throw new Exception("文件上传失败");
    }

    @Override
    public String generatePresignedGetUrl(String bucketName, String fileName) throws Exception {
        String url = S3_BASE_URL + "/generatePresignedGetUrl?bucketName=" + bucketName + "&fileName=" + fileName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new Exception("生成预签名URL失败");
        }
    }

    @Override
    public String generatePresignedPutUrl(String bucketName, String fileName) throws Exception {
        String url = S3_BASE_URL + "/generatePresignedPutUrl?bucketName=" + bucketName + "&fileName=" + fileName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new Exception("生成预签名URL失败");
        }
    }

    @Override
    public String listFiles(String bucketName) throws Exception {
        String url = S3_BASE_URL + "/list?bucketName=" + bucketName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    return jsonNode.get("data").asText();
                } else {
                    throw new Exception(jsonNode.get("msg").asText());
                }
            }
        }
        throw new Exception("文件列表获取失败");
    }

    @Override
    public String deleteFile(String bucketName, String fileName) throws Exception {
        String url = S3_BASE_URL + "/delete?bucketName=" + bucketName + "&fileName=" + fileName;
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.DELETE, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    return jsonNode.get("data").asText();
                } else {
                    throw new Exception(jsonNode.get("msg").asText());
                }
            }
        }
        throw new Exception("文件删除失败");
    }

    @Override
    public Map<String, String> getFileMetadata(String bucketName, String fileName) throws Exception {
        String url = S3_BASE_URL + "/metadata?bucketName=" + bucketName + "&fileName=" + fileName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    return objectMapper.readValue(jsonNode.get("data").toString(), Map.class);
                } else {
                    throw new Exception(jsonNode.get("msg").asText());
                }
            }
        }
        throw new Exception("获取文件元数据失败");
    }

    @Override
    public AjaxResult rename(String bucketName, String fileName, String newFileName) throws Exception {
        String url = S3_BASE_URL + "/rename?bucketName=" + bucketName + "&currentFileName=" + fileName + "&newFileName=" + newFileName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(response.getBody());
            } catch (JsonProcessingException e) {
                return AjaxResult.warn("JSON解析失败");
            }
            if (jsonNode.has("code")) {
                if (jsonNode.get("code").asInt() == 200) {
                    return AjaxResult.success(jsonNode.get("msg").asText(), jsonNode.get("data").asText());
                } else {
                    return AjaxResult.warn(jsonNode.get("msg").asText());
                }
            }
        }
        return AjaxResult.error("文件重命名失败");
    }
}