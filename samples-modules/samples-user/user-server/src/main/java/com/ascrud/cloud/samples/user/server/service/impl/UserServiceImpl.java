package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.user.server.repository.UserRepository;
import com.ascrud.cloud.samples.user.server.entity.User;
import com.ascrud.cloud.samples.user.server.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ascrud.cloud.samples.core.constant.Constants.UNDELETED_STATUS;

/**
 *
 *
 * @author walkman
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findUserList() {
        return userRepository.findAllByDelFlag(UNDELETED_STATUS);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsernameAndDelFlag(username, UNDELETED_STATUS);
    }

    /**
     * 根据用户Id, 获取用户详情
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(String userId) {
        return userRepository.findByUserIdAndDelFlag(userId, UNDELETED_STATUS);
    }

    /**
     * 创建或更新用户
     *
     * @param user
     * @return
     */
    @Override
    public User createOrUpdateUser(User user) {
        if (StringUtils.isBlank(user.getUserId())) {
            user.setUserId(UUID.randomUUID().toString());
        }
        return userRepository.save(user);
    }

}
