package com.ascrud.cloud.samples.product.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 *
 * @author walkman
 */
@Data
public class ProductOutput implements Serializable {
    private static final long serialVersionUID = 5528127681224597931L;

    private String productId;

    private String productName;

    private String productDesc;

    private BigDecimal price;

    private long stock;

}
