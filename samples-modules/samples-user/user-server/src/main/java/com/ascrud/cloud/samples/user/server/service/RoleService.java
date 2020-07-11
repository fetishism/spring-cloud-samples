package com.ascrud.cloud.samples.user.server.service;

import com.ascrud.cloud.samples.user.server.entity.Role;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
public interface RoleService {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 根据角色id, 查询角色详情
     *
     * @param roleId 角色id
     * @return
     */
    Role findByRoleId(String roleId);

    /**
     * 根据角色name, 查询角色详情
     *
     * @param roleName 角色name
     * @return
     */
    Role findByRoleName(String roleName);

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    Role saveOne(Role role);

}
