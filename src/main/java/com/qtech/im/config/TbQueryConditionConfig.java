package com.qtech.im.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/11/21 10:26:09
 * desc   :
 */

@Configuration
@ConfigurationProperties(prefix = "eqs")
@Getter
@Setter
public class TbQueryConditionConfig {
    private String deptName;
    private List<String> deptNames;
    private List<String> deviceTypes;
}
