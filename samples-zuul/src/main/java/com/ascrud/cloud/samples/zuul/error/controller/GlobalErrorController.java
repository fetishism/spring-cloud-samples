package com.ascrud.cloud.samples.zuul.error.controller;

import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 进入到filter的异常处理，
 *
 * {@link  org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter}
 * {@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController}
 * {@link org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration#basicErrorController}
 *
 * @author walkman
 */
@RestController
public class GlobalErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResultVO error(HttpServletRequest request, HttpServletResponse response) {
        return ResultVOUtils.error("自定义错误信息");
    }
}
