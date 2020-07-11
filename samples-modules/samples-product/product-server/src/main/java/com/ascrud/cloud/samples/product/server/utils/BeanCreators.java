package com.ascrud.cloud.samples.product.server.utils;

import com.ascrud.cloud.samples.core.constant.Constants;
import com.ascrud.cloud.samples.product.server.entity.Product;

import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 *
 *
 * @author walkman
 */
public final class BeanCreators extends com.ascrud.cloud.samples.product.common.utils.BeanCreators {

    public static Product createProduct() {
        return new Product();
    }

    public static Product createInitProduct() {
        Product product = new Product();
        product.setProductId(EMPTY);
        product.setDelFlag(Constants.UNDELETED_STATUS);
        return product;
    }
}
