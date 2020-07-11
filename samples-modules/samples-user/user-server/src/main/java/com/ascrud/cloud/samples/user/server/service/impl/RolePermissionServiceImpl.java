package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.user.server.repository.RolePermissionRepository;
import com.ascrud.cloud.samples.user.server.entity.RolePermission;
import com.ascrud.cloud.samples.user.server.service.RolePermissionService;
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
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    /**
     * 查询角色权限关系列表
     *
     * @return
     */
    @Override
    public List<RolePermission> findAll() {
        return rolePermissionRepository.findAllByDelFlag(UNDELETED_STATUS);
    }

    /**
     * 根据角色权限关系id, 查询角色权限关系详情
     *
     * @param rolePermissionId 角色id
     * @return
     */
    @Override
    public RolePermission findByRolePermissionId(String rolePermissionId) {
        return rolePermissionRepository.findByRolePermissionIdAndDelFlag(rolePermissionId, UNDELETED_STATUS);
    }

    /**
     * 根据角色id, 查询该角色对应的角色权限关系
     *
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<RolePermission> findAllByRoleId(String roleId) {
        return rolePermissionRepository.findAllByRoleIdAndDelFlag(roleId, UNDELETED_STATUS);
    }

    /**
     * 保存角色权限关系
     *
     * @param rolePermission
     * @return
     */
    @Override
    public RolePermission saveOne(RolePermission rolePermission) {
        if (StringUtils.isBlank(rolePermission.getRolePermissionId())) {
            rolePermission.setRolePermissionId(UUID.randomUUID().toString());
        }
        return rolePermissionRepository.save(rolePermission);
    }

    /**
     * 保存角色权限关系
     *
     * @param rolePermissionList
     * @return
     */
    @Override
    public List<RolePermission> saveAll(List<RolePermission> rolePermissionList) {
        return rolePermissionRepository.saveAll(rolePermissionList);
    }

    /**
     * 根据给定的角色id数组, 查询这些角色下的权限列表
     *
     * @param roleIds 角色id数组
     * @return
     */
    @Override
    public List<RolePermission> findAllByRoleIds(List<String> roleIds) {
        return rolePermissionRepository.findAllByRoleIdInAndDelFlag(roleIds, UNDELETED_STATUS);
    }
}
