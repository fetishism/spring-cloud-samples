package com.ascrud.cloud.samples.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 集成alibaba nacos config实现动态配置
 *
 * @author walkman
 */
@RestController
@RequestMapping("config")
@RefreshScope
public class DynamicConfigController {

    @Value("${useLocalCache:\"\"}")
    private String useLocalCache;

    @GetMapping("list")
    public String test() {
        return useLocalCache;
    }
}
