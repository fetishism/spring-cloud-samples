package com.ascrud.cloud.samples.order.server.controller;

import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.server.service.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboController {

    @Autowired
    private DubboService dubboService;

    @GetMapping("/dubbo")
    public ResultVO say() {
        return ResultVOUtils.success(dubboService.say("Kimmy"));
    }
}
