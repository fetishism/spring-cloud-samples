package com.ascrud.cloud.samples.user.server.repository;

import com.ascrud.cloud.samples.user.server.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    /**
     * 查询所有权限节点
     *
     * @param delFlag
     * @return
     */
    List<RolePermission> findAllByDelFlag(char delFlag);

    /**
     * 根据角色权限关系id, 查询角色权限关系详情
     *
     * @param rolePermissionId 角色id
     * @param delFlag
     * @return
     */
    RolePermission findByRolePermissionIdAndDelFlag(String rolePermissionId, char delFlag);

    /**
     * 根据角色id, 查询该角色下的权限列表
     *
     * @param roleId 角色id
     * @param delFlag
     * @return
     */
    List<RolePermission> findAllByRoleIdAndDelFlag(String roleId, char delFlag);

    /**
     * 根据给定的角色id数组, 查询这些角色下的权限列表
     *
     * @param roleIds 角色id数组
     * @param delFlag
     * @return
     */
    List<RolePermission> findAllByRoleIdInAndDelFlag(List<String> roleIds, char delFlag);

}
