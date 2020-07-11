package com.ascrud.cloud.samples.product.common.utils;

import com.ascrud.cloud.samples.product.common.entity.ProductInput;
import com.ascrud.cloud.samples.product.common.entity.ProductOutput;

/**
 *
 *
 * @author walkman
 */
public class BeanCreators {

    public static ProductOutput createProductOutput() {
        return new ProductOutput();
    }

    public static ProductInput createProductInput() {
        return new ProductInput();
    }

}
