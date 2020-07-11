package com.ascrud.cloud.samples.order.server.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义服务降级机制
 *
 * @author walkman
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
@RefreshScope
public class HystrixController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${defaultText:\"\"}")
    private String defaultText;

    /**
     * 该方法中出现异常时，默认调用{@link #defaultFallback}方法
     * @param num
     * @return
     */
    @HystrixCommand
    @GetMapping("test")
    public String test(@RequestParam("num") Integer num) {

        if (num % 2 == 0) {
            throw new RuntimeException();
        }

        return "success";
    }

    /**
     * 该方法中出现异常时，默认调用{@link #fallBack}方法
     *
     * 注意：{@link #fallBack}方法参数必须与本方法一致
     * @param num
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallBack")
    @GetMapping("test2")
    public String test2(@RequestParam("num") Integer num) {

        if (num % 2 == 0) {
            throw new RuntimeException();
        }

        return "success";
    }

    /**
     * 该方法中出现异常时，默认调用{@link #fallBack2}方法
     * @param num
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallBack2")
    @GetMapping("test3")
    public String test3(@RequestParam("num") Integer num) {

        if (num % 2 == 0) {
            throw new RuntimeException();
        }
        return "success";
    }

    private String fallBack(Integer num) {
        log.info("application.name = {}, num = {}", applicationName, num);
        return defaultText;
    }

    private String fallBack2() {
        return "太拥挤了, 请稍后重试~~~";
    }

    private String defaultFallback() {
        return "默认提示: 太拥挤了, 请稍后重试~~~";
    }
}
