package com.zy.service;

import com.zy.common.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author zhangyu
 */
@Slf4j
@MapperScan(value={"com.zy.service.**.mapper*"})
@SpringBootApplication
public class ServiceMainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext application =  SpringApplication.run(ServiceMainApplication.class, args);
        Environment env = application.getEnvironment();
//        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = ConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Jeecg-Boot is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/doc.html\n" +
//                "External: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
//                "文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
