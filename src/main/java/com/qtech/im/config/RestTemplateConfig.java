package com.qtech.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/09/08 17:41:32
 * desc   :
 */

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 添加所需的消息转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        // 添加默认转换器
        messageConverters.add(new StringHttpMessageConverter()); // 支持字符串的转换
        messageConverters.add(new MappingJackson2HttpMessageConverter()); // 支持 JSON 的转换
        messageConverters.add(new FormHttpMessageConverter()); // 支持 multipart/form-data 转换
        messageConverters.add(new ByteArrayHttpMessageConverter()); // 添加 ByteArrayHttpMessageConverter 以支持 byte[] 类型

        // 设置转换器
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}