package com.ascrud.cloud.samples.product.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品实体类
 *
 * @author walkman
 */
@Data
@ToString
@Entity
public class Product {

    /**
     * 商品ID
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private long stock;

    /**
     * 删除标识
     */
    private char delFlag;

}
