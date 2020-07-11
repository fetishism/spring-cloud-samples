package com.ascrud.cloud.samples.order.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 订单服务API，供其他模块调用
 *
 * @author walkman
 */
@FeignClient("order")
public interface OrderClient {

}
