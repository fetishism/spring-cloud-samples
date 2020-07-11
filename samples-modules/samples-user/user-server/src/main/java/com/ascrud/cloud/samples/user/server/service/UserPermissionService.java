package com.ascrud.cloud.samples.user.server.service;

import com.ascrud.cloud.samples.user.server.entity.UserPermission;

import java.util.List;

/**
 * @author walkman
 */
public interface UserPermissionService {

    /**
     * 查询用户权限关系列表
     *
     * @return
     */
    List<UserPermission> findAll();

    /**
     * 根据用户权限关系id, 查询用户权限关系详情
     *
     * @param userPermissionId 用户权限id
     * @return
     */
    UserPermission findByUserPermissionId(String userPermissionId);

    /**
     * 根据用户id, 查询该用户对应的角色权限关系
     *
     * @param userId 用户id
     * @return
     */
    List<UserPermission> findAllByUserId(String userId);

    /**
     * 保存用户权限关系
     *
     * @param userPermission
     * @return
     */
    UserPermission saveOne(UserPermission userPermission);

    /**
     * 保存用户权限关系
     *
     * @param userPermissionList
     * @return
     */
    List<UserPermission> saveAll(List<UserPermission> userPermissionList);

}
