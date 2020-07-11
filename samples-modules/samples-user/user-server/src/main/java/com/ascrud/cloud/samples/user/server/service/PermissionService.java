package com.ascrud.cloud.samples.user.server.service;

import com.ascrud.cloud.samples.user.server.entity.Permission;

import java.util.List;

/**
 * @author walkman
 */
public interface PermissionService {

    /**
     * 查询所有权限节点
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 查询权限子节点
     *
     * @param parentId
     * @return
     */
    List<Permission> findAllByParentId(String parentId);

    /**
     * 根据权限id, 查询权限节点详情
     *
     * @param permissionId
     * @return
     */
    Permission findByPermissionId(String permissionId);

    /**
     * 根据权限id集合, 查询权限详情列表
     *
     * @param permissionIds
     * @return
     */
    List<Permission> findAllByPermissionIds(List<String> permissionIds);

    /**
     * 根据权限name, 查询权限节点详情
     *
     * @param permissionName
     * @return
     */
    Permission findByPermissionName(String permissionName);

    /**
     * 保存权限节点
     *
     * @param permission
     * @return
     */
    Permission saveOne(Permission permission);

    /**
     * 保存权限节点
     *
     * @param permissions
     * @return
     */
    List<Permission> saveAll(List<Permission> permissions);
}
