package com.ascrud.cloud.samples.order.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author walkman
 */
@Data
public class OrderDetailOutput implements Serializable {
    private static final long serialVersionUID = 3688737926170402152L;

    private String orderDetailId;

    /**
     * 订单主表Id
     */
    private String orderId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private String productQuantity;
}
