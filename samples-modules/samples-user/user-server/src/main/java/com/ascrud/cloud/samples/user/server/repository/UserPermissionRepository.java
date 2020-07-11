package com.ascrud.cloud.samples.user.server.repository;

import com.ascrud.cloud.samples.user.server.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 */
@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, String> {

    /**
     * 查询所有权限节点
     *
     * @param delFlag
     * @return
     */
    List<UserPermission> findAllByDelFlag(char delFlag);

    /**
     * 根据用户权限关系id, 查询用户权限关系详情
     *
     * @param userPermissionId 用户权限关系id
     * @param delFlag
     * @return
     */
    UserPermission findByUserPermissionIdAndDelFlag(String userPermissionId, char delFlag);

    /**
     * 根据用户id, 查询用户权限关系详情
     *
     * @param userId  用户id
     * @param delFlag
     * @return
     */
    List<UserPermission> findAllByUserIdAndDelFlag(String userId, char delFlag);

}
