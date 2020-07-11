package com.ascrud.cloud.samples.product.common.entity;

import com.ascrud.cloud.samples.core.validation.constraints.StringMaxLength;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 *
 * @author walkman
 */
@Data
public class ProductInput implements Serializable {
    private static final long serialVersionUID = 7909293927779853866L;

    private String productId;

    @NotBlank(message = "商品名不能为空")
    private String productName;

    @StringMaxLength(value = 12, message = "商品描述不能多于{value}个字符")
    private String productDesc;

    @DecimalMin(value = "0.00", message = "商品价格必须大于{value}", inclusive = false)
    private BigDecimal price;

    @Min(value = 1, message = "库存不能小于{value}")
    private long stock;
}
