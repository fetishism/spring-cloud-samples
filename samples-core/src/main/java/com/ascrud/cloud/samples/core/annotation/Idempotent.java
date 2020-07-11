package com.ascrud.cloud.samples.core.annotation;

import com.ascrud.cloud.samples.core.service.IdempotentService;
import com.ascrud.cloud.samples.core.service.impl.DefaultIdempotentServiceImpl;

import java.lang.annotation.*;

/**
 * 幂等性接口
 *
 * @author walkman
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {
    Class<? extends IdempotentService> fallback() default DefaultIdempotentServiceImpl.class;

    IdempotentParam[] params() default {};
}
