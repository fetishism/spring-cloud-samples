package com.ascrud.cloud.samples.zuul.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ascrud.cloud.samples.core.exception.FallbackException;
import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.user.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.ascrud.cloud.see.auth.service.UserPermissionService;

/**
 *
 *
 * @author walkman
 */
//@Service
//@Slf4j
//public class UserPermissionServiceImpl implements UserPermissionService {
//
//    @Autowired
//    private UserClient userClient;
//
//    /**
//     * 根据用户名获取用户所有权限
//     *
//     * @param userId
//     * @return
//     */
//    @HystrixCommand(fallbackMethod = "getUserPermissionByUserIdFallbackMethod")
//    @Override
//    public ResultVO getUserPermissionByUserId(String userId) {
//        ResultVO resultVO = userClient.getUserPermissionByUserId(userId);
//        if (resultVO == null) {
//            throw new FallbackException();
//        }
//        return resultVO;
//    }
//
//    public ResultVO getUserPermissionByUserIdFallbackMethod(String username) {
//        // todo
//        return ResultVOUtils.error("getUserPermissionByUserIdFallbackMethod");
//    }
//}
