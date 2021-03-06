package com.ascrud.cloud.samples.order.server;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.ascrud.cloud.samples.product.client")
@ComponentScan(basePackages = "com.ascrud.cloud.samples")
@SpringCloudApplication
@EnableSwagger2Doc
public class OrderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
    }

}
