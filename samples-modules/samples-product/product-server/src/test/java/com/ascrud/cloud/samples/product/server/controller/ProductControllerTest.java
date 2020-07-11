package com.ascrud.cloud.samples.product.server.controller;

import com.ascrud.cloud.samples.product.server.ProductServerApplicationTests;
import com.ascrud.cloud.samples.product.server.service.ProductService;
import com.ascrud.cloud.samples.product.server.utils.BeanCreators;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;

/**
 * @SpringBootTest 不能和  @WebMvcTest 同时使用
 * 如果使用MockMvc对象的话，需要另外加上@AutoConfigureMockMvc注解
 *
 * @author walkman
 */
@WebMvcTest
public class ProductControllerTest extends ProductServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    /**
     * <a href="https://github.com/jayway/JsonPath">JsonPath</a>
     * @throws Exception
     */
    @Test
    public void getAllProduct() throws Exception {
        given(this.productService.getAllProduct())
                .willReturn(Arrays.asList(BeanCreators.createProduct(), BeanCreators.createProduct()));
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg", Matchers.is("success")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.hasSize(2)));
    }

    @Test
    public void createProduct() {

    }

    @Test
    public void getProductByProductId() {
    }

    @Test
    public void updateProduct2() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}
