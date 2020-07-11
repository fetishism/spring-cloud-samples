package com.ascrud.cloud.samples.core.service.impl;

import com.ascrud.cloud.samples.core.service.IdempotentService;
import org.springframework.stereotype.Service;

@Service
public class DefaultIdempotentServiceImpl implements IdempotentService {
    @Override
    public boolean isIdempotent() {
        return false;
    }
}
