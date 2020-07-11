package com.ascrud.cloud.samples.product.server.dubbo;

import com.ascrud.cloud.samples.product.dubbo.DubboProductClient;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
public class DubboProductClientImpl implements DubboProductClient {

    @Override
    public String sayHiTo(String name) {
        return String.format("Hello, %s", name);
    }
}
