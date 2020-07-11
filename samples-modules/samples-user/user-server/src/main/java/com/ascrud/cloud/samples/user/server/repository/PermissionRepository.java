package com.ascrud.cloud.samples.user.server.repository;

import com.ascrud.cloud.samples.user.server.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    /**
     * 查询所有权限节点
     *
     * @param delFlag
     * @return
     */
    List<Permission> findAllByDelFlag(char delFlag);

    /**
     * 根据权限id, 查询权限节点详情
     *
     * @param permissionId
     * @param delFlag
     * @return
     */
    Permission findByPermissionIdAndDelFlag(String permissionId, char delFlag);

    /**
     * 根据权限id集合, 查询权限详情列表
     *
     * @param permissionIds
     * @param delFlag
     * @return
     */
    List<Permission> findAllByPermissionIdInAndDelFlag(List<String> permissionIds, char delFlag);

    /**
     * 根据权限name, 查询权限节点详情
     *
     * @param permissionName
     * @param delFlag
     * @return
     */
    Permission findByPermissionNameAndDelFlag(String permissionName, char delFlag);

    /**
     * 查询权限子节点
     *
     * @param parentId
     * @param delFlag
     * @return
     */
    List<Permission> findAllByParentIdAndDelFlag(String parentId, char delFlag);

}
