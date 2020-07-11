package com.ascrud.cloud.samples.gateway;

import com.ascrud.cloud.samples.gateway.config.Swagger2Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(Swagger2Properties.class)
public class SamplesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplesGatewayApplication.class, args);
    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
}
