package com.ascrud.cloud.samples.user.server.service;


import com.ascrud.cloud.samples.user.server.entity.User;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
public interface UserService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findUserList();

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return UserOutput
     */
    User findUserByUsername(String username);

    /**
     * 根据用户Id, 获取用户详情
     *
     * @param userId
     * @return UserOutput
     */
    User findByUserId(String userId);

    /**
     * 创建或更新用户
     *
     * @param user
     * @return UserOutput
     */
    User createOrUpdateUser(User user);

}
