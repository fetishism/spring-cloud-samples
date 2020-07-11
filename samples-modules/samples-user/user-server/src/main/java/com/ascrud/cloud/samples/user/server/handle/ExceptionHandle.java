package com.ascrud.cloud.samples.user.server.handle;

import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ascrud.cloud.samples.core.enums.ResultEnum.PARAM_ERROR;

/**
 *
 * @author walkman
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("uri={}, method={}, msg={}", request.getRequestURI(), request.getMethod(), e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResultVOUtils.error(PARAM_ERROR);
        }
        return ResultVOUtils.error(e.getMessage());
    }

}
