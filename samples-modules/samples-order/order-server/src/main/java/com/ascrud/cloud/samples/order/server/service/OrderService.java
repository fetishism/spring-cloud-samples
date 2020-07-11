package com.ascrud.cloud.samples.order.server.service;

import com.ascrud.cloud.samples.product.common.entity.ProductOutput;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.common.entity.OrderDetailInput;
import com.ascrud.cloud.samples.order.server.entity.OrderDetail;
import com.ascrud.cloud.samples.order.server.entity.OrderMain;

import java.util.List;
import java.util.Map;

/**
 * @author walkman
 */
public interface OrderService {

    /**
     * @return
     */
    List<OrderMain> getAllOrderMain();

    /**
     * 创建订单
     *
     * @return
     */
    OrderMain createOrder(List<OrderDetailInput> orderDetailInputList, Map<String, ProductOutput> productOutputMap);

    /**
     * @param orderId
     * @return
     */
    List<OrderDetail> getAllOrderDetailByOrderId(String orderId);

    /**
     * @param id
     * @return
     */
    OrderMain getOrderMainById(String id);

    /**
     * @param orderMain
     * @return
     */
    OrderMain saveOne(OrderMain orderMain);

    /**
     * @param productIdList
     * @return
     */
    ResultVO getProductListByProductIds(List<String> productIdList);
}
