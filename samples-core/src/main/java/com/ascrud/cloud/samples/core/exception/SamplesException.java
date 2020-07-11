package com.ascrud.cloud.samples.core.exception;

/**
 * 异常基类
 *
 * @author walkman
 */
public class SamplesException extends RuntimeException {
    private static final long serialVersionUID = 3581367359864724861L;

    protected Integer code;

    public SamplesException() {
    }

    public SamplesException(Integer code) {
        this.code = code;
    }

    public SamplesException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SamplesException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
