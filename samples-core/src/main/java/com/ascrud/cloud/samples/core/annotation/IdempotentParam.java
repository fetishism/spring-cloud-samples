package com.ascrud.cloud.samples.core.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotentParam {
    String key() default "";

    String val();
}
