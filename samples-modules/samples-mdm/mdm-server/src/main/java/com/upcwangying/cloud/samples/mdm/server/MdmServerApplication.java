package com.ascrud.cloud.samples.mdm.server;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
public class MdmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdmServerApplication.class, args);
    }

}
