package com.ascrud.cloud.samples.user.server.service;

import com.ascrud.cloud.samples.user.server.entity.RolePermission;

import java.util.List;

/**
 * @author walkman
 */
public interface RolePermissionService {

    /**
     * 查询角色权限关系列表
     *
     * @return
     */
    List<RolePermission> findAll();

    /**
     * 根据角色权限关系id, 查询角色权限关系详情
     *
     * @param rolePermissionId 角色id
     * @return
     */
    RolePermission findByRolePermissionId(String rolePermissionId);

    /**
     * 根据角色id, 查询该角色对应的角色权限关系
     *
     * @param roleId 角色id
     * @return
     */
    List<RolePermission> findAllByRoleId(String roleId);

    /**
     * 保存角色权限关系
     *
     * @param rolePermission
     * @return
     */
    RolePermission saveOne(RolePermission rolePermission);

    /**
     * 保存角色权限关系
     *
     * @param rolePermissionList
     * @return
     */
    List<RolePermission> saveAll(List<RolePermission> rolePermissionList);

    /**
     * 根据给定的角色id数组, 查询这些角色下的权限列表
     *
     * @param roleIds 角色id数组
     * @return
     */
    List<RolePermission> findAllByRoleIds(List<String> roleIds);

}
