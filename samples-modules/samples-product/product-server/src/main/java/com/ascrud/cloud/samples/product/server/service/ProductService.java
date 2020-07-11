package com.ascrud.cloud.samples.product.server.service;

import com.ascrud.cloud.samples.product.server.entity.Product;

import java.util.List;

/**
 * 商品服务业务层
 *
 * @author walkman
 */
public interface ProductService {

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Product> getAllProduct();

    /**
     * 查询商品明细
     *
     * @return
     */
    Product getProductById(String id);

    /**
     * 查询商品明细
     *
     * @return
     */
    List<Product> getProductById(List<String> productIdList);

    /**
     * 新增、更新、删除商品
     *
     * @return
     */
    Product saveOne(Product product);

    /**
     * 批量新增、更新、删除商品
     *
     * @return
     */
    List<Product> saveAll(List<Product> productList);
}
