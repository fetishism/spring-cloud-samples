package com.ascrud.cloud.samples.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SamplesAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplesAdminApplication.class, args);
    }

}
