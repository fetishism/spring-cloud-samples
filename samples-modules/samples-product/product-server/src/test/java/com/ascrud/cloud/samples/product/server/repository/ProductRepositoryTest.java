package com.ascrud.cloud.samples.product.server.repository;

import com.ascrud.cloud.samples.product.server.ProductServerApplicationTests;
import com.ascrud.cloud.samples.product.server.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 *
 *
 * @author walkman
 */
@DataJpaTest
@Slf4j
public class ProductRepositoryTest extends ProductServerApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    /**
     * 在每个测试方法前执行，
     * 一般用来初始化方法（比如我们在测试别的方法时，类中与其他测试方法共享的值已经被改变，为了保证测试结果的有效性，我们会在@Before注解的方法中重置数据）
     */
    @Before
    public void init() {
        log.info("~~~~~~~~单个测试方法执行前调用~~~~~~~~");
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("product_test");
        product.setProductDesc("商品測試");
        product.setStock(1);
        product.setPrice(BigDecimal.TEN);
        product.setDelFlag('0');
        this.entityManager.persist(product);
    }

    /**
     * 在每个测试方法后执行，在方法执行完成后要做的事情
     */
    @After
    public void after() {
        this.entityManager.clear();
        log.info("~~~~~~~~单个测试方法执行后调用~~~~~~~~");
    }


    @Test
    public void findAllByDelFlagIs() {
        List<Product> productList = productRepository.findAllByDelFlag('0');
        Assert.assertEquals("获取所有商品列表", 1, productList.size());
    }

}
