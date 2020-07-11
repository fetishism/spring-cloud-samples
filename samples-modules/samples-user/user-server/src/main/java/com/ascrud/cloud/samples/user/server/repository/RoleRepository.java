package com.ascrud.cloud.samples.user.server.repository;

import com.ascrud.cloud.samples.user.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author walkman
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    /**
     * 查询所有权限节点
     *
     * @param delFlag
     * @return
     */
    List<Role> findAllByDelFlag(char delFlag);

    /**
     * 根据角色id, 查询角色详情
     *
     * @param roleId 角色id
     * @param delFlag
     * @return
     */
    Role findByRoleIdAndDelFlag(String roleId, char delFlag);

    /**
     * 根据角色名, 查询角色详情
     *
     * @param roleName 角色名
     * @param delFlag
     * @return
     */
    Role findByRoleNameAndDelFlag(String roleName, char delFlag);

}
