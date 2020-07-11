package com.ascrud.cloud.samples.product.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

/**
 *
 *
 * @author walkman
 */
public class AuthInterceptor implements RequestInterceptor {

    public AuthInterceptor() {
    }

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map<String, Collection<String>> headers =  template.headers();
        String method = template.method();
        String url = template.url();

    }
}
