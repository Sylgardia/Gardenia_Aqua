package com.zy.service_main9001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zhangyu
 */
@MapperScan("com.zy.service_main9001.mapper")
@SpringBootApplication
public class ServiceMain9001Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMain9001Application.class, args);
    }
}
