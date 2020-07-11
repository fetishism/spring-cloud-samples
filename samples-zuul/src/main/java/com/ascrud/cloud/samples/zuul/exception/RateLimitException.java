package com.ascrud.cloud.samples.zuul.exception;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;

/**
 *
 *
 * @author walkman
 */
public class RateLimitException extends ZuulRuntimeException {

    private static final String MESSAGE = "RateLimitException";

    public RateLimitException() {
        super(new ZuulException(HttpStatus.TOO_MANY_REQUESTS.toString(), HttpStatus.TOO_MANY_REQUESTS.value(), MESSAGE));
    }
}
