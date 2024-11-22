package com.qtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

/**
 * 启动程序
 * 
 * @author qtech
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ImApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ImApplication.class, args);
        System.out.println("智能制造系统启动成功！");
    }
}
