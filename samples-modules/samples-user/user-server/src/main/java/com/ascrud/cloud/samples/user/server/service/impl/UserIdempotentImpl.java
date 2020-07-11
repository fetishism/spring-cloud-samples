package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.core.service.IdempotentService;
import org.springframework.stereotype.Service;

@Service
public class UserIdempotentImpl implements IdempotentService {

    @Override
    public boolean isIdempotent() {
        return true;
    }
}
