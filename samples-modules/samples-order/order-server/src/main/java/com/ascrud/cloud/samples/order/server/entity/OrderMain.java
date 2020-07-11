package com.ascrud.cloud.samples.order.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 *
 *
 * @author walkman
 */
@Entity
public class OrderMain {

    /**
     * 订单ID
     */
    @Id
    private String orderId;

    /**
     * 购买人
     */
    private String buyer;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 删除标识
     */
    private char delFlag;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public char getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(char delFlag) {
        this.delFlag = delFlag;
    }
}
