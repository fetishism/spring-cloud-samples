package com.ascrud.cloud.samples.order.server.utils;

import com.ascrud.cloud.samples.order.server.entity.OrderDetail;
import com.ascrud.cloud.samples.order.server.entity.OrderMain;

import static com.ascrud.cloud.samples.core.constant.Constants.UNDELETED_STATUS;

/**
 * @author walkman
 */
public class BeanCreators extends com.ascrud.cloud.samples.order.common.utils.BeanCreators {
    public static OrderMain createOrderMain() {
        OrderMain orderMain = new OrderMain();
        orderMain.setDelFlag(UNDELETED_STATUS);
        return orderMain;
    }

    public static OrderDetail createOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDelFlag(UNDELETED_STATUS);
        return orderDetail;
    }
}
