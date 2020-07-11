package com.ascrud.cloud.samples.product.client;

import com.ascrud.cloud.samples.product.client.hystrix.strategy.RequestAttributeHystrixConcurrencyStrategy;
import com.ascrud.cloud.samples.product.client.interceptor.AuthInterceptor;
import com.ascrud.cloud.samples.product.client.interceptor.AuthInterceptor2;
import com.ascrud.cloud.samples.product.common.entity.ProductInput;
import feign.Logger;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 此接口供其他服务调用
 *
 * @author walkman
 */
@FeignClient(name = "product",
        fallback = ProductClient.ProductClientFallback.class,
        configuration = ProductClient.ProductClientConfiguration.class
        )
public interface ProductClient {

    @GetMapping(value = "/products/list/all")
    @ResponseBody
    ResultVO getProductListByProductIds(@RequestParam("productIds") List<String> productIds);

    @PutMapping(value = "/products/save/all")
    @ResponseBody
    ResultVO updateProduct(@RequestBody List<ProductInput> productInputList);

    @Component
    static class ProductClientFallback implements ProductClient {

        @Override
        public ResultVO getProductListByProductIds(List<String> productIds) {
            return null;
        }

        @Override
        public ResultVO updateProduct(List<ProductInput> productInputList) {
            return null;
        }

    }

    @Configuration
    static class ProductClientConfiguration {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public AuthInterceptor authInterceptor() {
            return new AuthInterceptor();
        }

        @Bean
        public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration() {
            return new RequestAttributeHystrixConcurrencyStrategy();
        }

        @Bean
        public AuthInterceptor2 authInterceptor2() {
            return new AuthInterceptor2();
        }

        @Bean
        @ConditionalOnBean(AuthInterceptor2.class)
        public okhttp3.OkHttpClient okHttpClient(AuthInterceptor2 authInterceptor2){
            okhttp3.OkHttpClient.Builder ClientBuilder = new okhttp3.OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS) //读取超时
                    .connectTimeout(10, TimeUnit.SECONDS) //连接超时
                    .writeTimeout(60, TimeUnit.SECONDS) //写入超时
                    .connectionPool(new ConnectionPool(10 /*maxIdleConnections*/, 3, TimeUnit.MINUTES))
                    .addInterceptor(authInterceptor2);
            return ClientBuilder.build();
        }
    }

}
