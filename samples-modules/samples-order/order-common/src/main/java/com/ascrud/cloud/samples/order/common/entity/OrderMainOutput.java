package com.ascrud.cloud.samples.order.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author walkman
 */
@Data
public class OrderMainOutput implements Serializable {
    private static final long serialVersionUID = -4510810296759591299L;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 购买人
     */
    private String buyer;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

}
