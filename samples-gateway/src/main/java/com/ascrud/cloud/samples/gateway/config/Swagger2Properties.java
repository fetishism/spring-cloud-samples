package com.ascrud.cloud.samples.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author walkman
 */
@Configuration
@PropertySource("classpath:bootstrap.yml")
@ConfigurationProperties(prefix = "samples")
public class Swagger2Properties {

    private List<String> excludeServices;

    public List<String> getExcludeServices() {
        return excludeServices;
    }

    public void setExcludeServices(List<String> excludeServices) {
        this.excludeServices = excludeServices;
    }
}
