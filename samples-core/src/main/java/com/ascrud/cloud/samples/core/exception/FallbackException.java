package com.ascrud.cloud.samples.core.exception;

/**
 * FallBack异常类
 *
 * @author walkman
 */
public class FallbackException extends SamplesException {
    private static final long serialVersionUID = 3581367359864724861L;

    public FallbackException() {
        super();
    }

    public FallbackException(Integer code) {
        super(code);
    }

    public FallbackException(Integer code, String message) {
        super(code, message);
    }

    public FallbackException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
