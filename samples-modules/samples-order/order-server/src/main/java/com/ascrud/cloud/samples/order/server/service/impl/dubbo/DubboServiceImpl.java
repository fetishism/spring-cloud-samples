package com.ascrud.cloud.samples.order.server.service.impl.dubbo;

import com.ascrud.cloud.samples.order.server.service.DubboService;
import com.ascrud.cloud.samples.product.dubbo.DubboProductClient;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class DubboServiceImpl implements DubboService {

    @Reference
    private DubboProductClient productClient;

    public String say(String name) {
        return productClient.sayHiTo(name);
    }
}
