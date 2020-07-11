package com.ascrud.cloud.samples.user.server.repository;

import com.ascrud.cloud.samples.user.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 查询所有用户
     *
     * @param delFlag
     * @return
     */
    List<User> findAllByDelFlag(char delFlag);

    /**
     * 根据用户名和密码查找用户
     *
     * @param username
     * @param delFlag
     * @return
     */
    User findByUsernameAndDelFlag(String username, char delFlag);

    /**
     * 根据用户Id, 获取用户详情
     *
     * @param userId
     * @param delFlag
     * @return
     */
    User findByUserIdAndDelFlag(String userId, char delFlag);

}
