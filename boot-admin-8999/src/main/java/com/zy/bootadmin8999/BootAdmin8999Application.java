package com.zy.bootadmin8999;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyu
 */
@EnableAdminServer
@SpringBootApplication
public class BootAdmin8999Application {

    public static void main(String[] args) {
        SpringApplication.run(BootAdmin8999Application.class, args);
    }

}
