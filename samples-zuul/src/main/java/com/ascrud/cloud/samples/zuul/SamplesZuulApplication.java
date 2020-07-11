package com.ascrud.cloud.samples.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.ascrud.cloud.samples.user.client"})
@ComponentScan(basePackages = {"com.ascrud.cloud.samples"})
@SpringCloudApplication
public class SamplesZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplesZuulApplication.class, args);
    }

}
