package com.ascrud.cloud.samples.product.server.service.impl;

import com.ascrud.cloud.samples.core.constant.Constants;
import com.ascrud.cloud.samples.product.server.repository.ProductRepository;
import com.ascrud.cloud.samples.product.server.entity.Product;
import com.ascrud.cloud.samples.product.server.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 *
 * @author walkman
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAllByDelFlag(Constants.UNDELETED_STATUS);
    }

    /**
     * 查询商品明细
     * @return
     */
    @Override
    public Product getProductById(String productId) {
        return productRepository.findByProductIdAndDelFlag(productId, Constants.UNDELETED_STATUS);
    }

    /**
     * 查询商品明细
     * @return
     */
    @Override
    public List<Product> getProductById(List<String> productIdList) {
        return productRepository.findAllByProductIdInAndDelFlag(productIdList, Constants.UNDELETED_STATUS);
    }

    /**
     * 新增、更新、删除商品
     * @return
     */
    @Override
    public Product saveOne(Product product) {
        if (StringUtils.isBlank(product.getProductId())) {
            product.setProductId(UUID.randomUUID().toString());
        }
        return productRepository.save(product);
    }

    /**
     * 批量新增、更新、删除商品
     * @return
     */
    @Override
    public List<Product> saveAll(List<Product> productList) {
        productList = productList.stream().map(product -> {
            if (StringUtils.isBlank(product.getProductId())) {
                product.setProductId(UUID.randomUUID().toString());
            }
            return product;
        }).collect(Collectors.toList());
        return productRepository.saveAll(productList);
    }
}
