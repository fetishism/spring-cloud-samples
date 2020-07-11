package com.ascrud.cloud.samples.order.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 超时配置
 *
 * @author walkman
 */
@RestController
@RequestMapping("timeout")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixTimeoutController {

    @Autowired
    private OrderService orderService;


    /**
     * 属性查看{@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager#EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS},
     * 更多设置请查看{@link com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager}
     * 默认设置请查看{@link com.netflix.hystrix.HystrixCommandProperties}
     * @return
     */
    @GetMapping("test")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @HystrixCommand(commandKey = "HystrixTimeoutController_test")
    public String test() {
//        orderService.getAllOrder();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://127.0.0.1:9092/product", ResultVO.class);

        return "success";
    }

    /**
     * Hystrix与Feign集成
     * 1、启用配置 feign.hystrix.enabled: true
     * 2、在ProductClient中定义fallback
     * @return
     */
    @GetMapping("test2")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @HystrixCommand(commandKey = "HystrixTimeoutController_test")
    public String test2() {
        orderService.getAllOrderMain();

        return "success";
    }

    private String defaultFallback() {
        return "默认提示: 太拥挤了, 请稍后重试~~~";
    }
}
