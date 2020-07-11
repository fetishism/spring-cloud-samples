package com.ascrud.cloud.samples.order.server.repository;

import com.ascrud.cloud.samples.order.server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单Id, 查询订单明细
     *
     * @param orderId
     * @param delFlag
     * @return
     */
    List<OrderDetail> findAllByOrderIdAndDelFlag(String orderId, char delFlag);

}
