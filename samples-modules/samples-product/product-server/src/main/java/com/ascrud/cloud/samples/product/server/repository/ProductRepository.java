package com.ascrud.cloud.samples.product.server.repository;

import com.ascrud.cloud.samples.product.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品服务持久层
 *
 * @author walkman
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * 通过所有商品
     *
     * @param delFlag 删除标识
     * @return
     */
    List<Product> findAllByDelFlag(char delFlag);

    /**
     * 通过商品ID，查找商品
     *
     * @param productId 商品ID
     * @param delFlag 删除标识
     * @return
     */
    Product findByProductIdAndDelFlag(String productId, char delFlag);

    /**
     * 通过商品ID，查找商品
     *
     * @param productIdList 商品ID列表
     * @param delFlag 删除标识
     * @return
     */
    List<Product> findAllByProductIdInAndDelFlag(List<String> productIdList, char delFlag);
}
