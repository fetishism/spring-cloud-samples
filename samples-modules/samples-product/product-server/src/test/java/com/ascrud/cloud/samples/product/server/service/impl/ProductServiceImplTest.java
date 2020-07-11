package com.ascrud.cloud.samples.product.server.service.impl;

import com.ascrud.cloud.samples.product.server.ProductServerApplicationTests;
import com.ascrud.cloud.samples.product.server.entity.Product;
import com.ascrud.cloud.samples.product.server.repository.ProductRepository;
import com.ascrud.cloud.samples.product.server.service.ProductService;
import com.ascrud.cloud.samples.product.server.utils.BeanCreators;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

/**
 *
 *
 * @author walkman
 */
@SpringBootTest
@Slf4j
public class ProductServiceImplTest extends ProductServerApplicationTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    /**
     * @Test(timeout = 1000):测试方法执行超过1000毫秒后算超时，测试将失败
     * @Test(expected = Exception.class):测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败
     * @Ignore("not ready yet") 执行测试时将忽略掉此方法，如果用于修饰类，则忽略整个类
     */
    @Test
    public void getAllProduct() {
        given(this.productRepository.findAllByDelFlag('0'))
                .willReturn(Arrays.asList(BeanCreators.createProduct(), BeanCreators.createProduct()));
        List<Product> productList = productService.getAllProduct();
        Assert.assertEquals("获取所有商品列表", 2, productList.size());
    }

    @Test
    public void getProductById() {

    }

    @Test
    public void insertOrUpdateProduct() {
    }
}
