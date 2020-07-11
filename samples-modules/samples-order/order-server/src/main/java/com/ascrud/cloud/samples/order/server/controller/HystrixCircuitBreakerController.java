package com.ascrud.cloud.samples.order.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ascrud.cloud.samples.order.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断测试类
 *
 * @author walkman
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@RequestMapping("breaker")
@Slf4j
public class HystrixCircuitBreakerController {

    @Autowired
    private OrderService orderService;

    /**
     * 更多设置请查看{@link com.netflix.hystrix.HystrixCommandProperties}
     * @return
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),       // 10 requests
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),    // 60%
    })
    @GetMapping("test")
    public String test(@RequestParam("num") Integer num) {
        if (num % 2 == 0) {
            orderService.getAllOrderMain();
        }
        return "success";
    }

    private String defaultFallback() {
        return "默认提示: 太拥挤了, 请稍后重试~~~";
    }

}
