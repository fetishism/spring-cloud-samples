package com.ascrud.cloud.samples.order.server.controller;

import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 *
 *
 * @author walkman
 */
@RestController
@RequestMapping("/tests")
@Slf4j
public class TestController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据订单Id, 查询订单明细
     * @return
     */
    @GetMapping
    public ResultVO getOrderDetailById() {
        return orderService.getProductListByProductIds(Arrays.asList("1", "2", "3"));
    }
}
