package com.ascrud.cloud.samples.zuul.error;

import com.netflix.zuul.exception.ZuulException;
import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 如果是请求zuul本身项目的一些异常需要使用@ControllerAdvice来实现，这个和普通的springMVC项目就一样了
 *
 * @author walkman
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResultVO handler(Exception e) {
        log.error("ExceptionHandle~~~~", e);
        if (e instanceof ZuulException) {
            return ResultVOUtils.error(((ZuulException) e).nStatusCode, ((ZuulException) e).errorCause);
        }
        return ResultVOUtils.error(-1, e.getMessage());
    }
}
