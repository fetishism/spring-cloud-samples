/*
 *
 * MIT License
 *
 * Copyright (c) 2019 cloud.upcwangying.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.upcwangying.cloud.samples.zuul.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.upcwangying.cloud.auth.service.UserPermissionService;
import com.upcwangying.cloud.samples.core.exception.FallbackException;
import com.upcwangying.cloud.samples.core.utils.ResultVOUtils;
import com.upcwangying.cloud.samples.core.vo.ResultVO;
import com.upcwangying.cloud.samples.user.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by WANGY
 *
 * @author WANGY
 * @date 2019/4/29 10:52
 */
@Service
@Slf4j
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserClient userClient;

    /**
     * 根据用户名获取用户所有权限
     *
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserPermissionByUserIdFallbackMethod")
    @Override
    public ResultVO getUserPermissionByUserId(String userId) {
        ResultVO resultVO = userClient.getUserPermissionByUserId(userId);
        if (resultVO == null) {
            throw new FallbackException();
        }
        return resultVO;
    }

    public ResultVO getUserPermissionByUserIdFallbackMethod(String username) {
        // todo
        return ResultVOUtils.error("getUserPermissionByUserIdFallbackMethod");
    }
}
