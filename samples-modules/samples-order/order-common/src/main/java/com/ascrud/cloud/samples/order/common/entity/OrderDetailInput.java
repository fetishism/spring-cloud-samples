package com.ascrud.cloud.samples.order.common.entity;

import com.ascrud.cloud.samples.core.validation.constraints.StringLength;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author walkman
 */
@Data
public class OrderDetailInput implements Serializable {
    private static final long serialVersionUID = 3688737926170402152L;

    /**
     * 商品Id
     */
    @StringLength(min = 36, max = 36, message = "商品id长度在[{min}, {max}]之间")
    private String productId;

    /**
     * 购买数量
     */
    @Min(value = 1, message = "购买商品数量不能小于{value}")
    private Long productQuantity;
}
