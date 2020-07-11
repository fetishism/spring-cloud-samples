package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.user.server.repository.RoleRepository;
import com.ascrud.cloud.samples.user.server.entity.Role;
import com.ascrud.cloud.samples.user.server.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.ascrud.cloud.samples.core.constant.Constants.UNDELETED_STATUS;

/**
 * @author walkman
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 查询所有权限节点
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleRepository.findAllByDelFlag(UNDELETED_STATUS);
    }

    /**
     * 根据权限id, 查询权限节点详情
     *
     * @param roleId
     * @return
     */
    @Override
    public Role findByRoleId(String roleId) {
        return roleRepository.findByRoleIdAndDelFlag(roleId, UNDELETED_STATUS);
    }

    /**
     * 根据权限name, 查询权限节点详情
     *
     * @param roleName
     * @return
     */
    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleNameAndDelFlag(roleName, UNDELETED_STATUS);
    }

    /**
     * 保存权限节点
     *
     * @param role
     * @return
     */
    @Override
    public Role saveOne(Role role) {
        if (StringUtils.isBlank(role.getRoleId())) {
            role.setRoleId(UUID.randomUUID().toString());
        }
        return roleRepository.save(role);
    }

}
