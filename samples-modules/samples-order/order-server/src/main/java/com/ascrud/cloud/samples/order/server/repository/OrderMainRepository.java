package com.ascrud.cloud.samples.order.server.repository;

import com.ascrud.cloud.samples.order.server.entity.OrderMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 */
@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, String> {
    /**
     * 通过所有订单
     *
     * @param delFlag 删除标识
     * @return
     */
    List<OrderMain> findAllByDelFlag(char delFlag);

    /**
     * 通过商品ID，查找商品
     *
     * @param orderMainId 订单主表ID
     * @param delFlag     删除标识
     * @return
     */
    OrderMain findByOrderIdAndDelFlag(String orderMainId, char delFlag);

}
