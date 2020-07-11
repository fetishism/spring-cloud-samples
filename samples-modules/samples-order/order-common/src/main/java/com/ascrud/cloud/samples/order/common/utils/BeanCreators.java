package com.ascrud.cloud.samples.order.common.utils;

import com.ascrud.cloud.samples.order.common.entity.OrderDetailOutput;
import com.ascrud.cloud.samples.order.common.entity.OrderMainOutput;

/**
 * @author walkman
 */
public class BeanCreators {
    public static OrderMainOutput ceateOrderMainOutput() {
        return new OrderMainOutput();
    }

    public static OrderDetailOutput ceateOrderDetailOutput() {
        return new OrderDetailOutput();
    }
}
