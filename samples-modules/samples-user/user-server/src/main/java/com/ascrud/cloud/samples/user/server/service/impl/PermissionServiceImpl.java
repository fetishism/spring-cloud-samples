package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.user.server.repository.PermissionRepository;
import com.ascrud.cloud.samples.user.server.entity.Permission;
import com.ascrud.cloud.samples.user.server.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 查询所有权限节点
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAllByDelFlag(UNDELETED_STATUS);
    }

    /**
     * 查询权限子节点
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Permission> findAllByParentId(String parentId) {
        return permissionRepository.findAllByParentIdAndDelFlag(parentId, UNDELETED_STATUS);
    }

    /**
     * 根据权限id, 查询权限节点详情
     *
     * @param permissionId
     * @return
     */
    @Override
    public Permission findByPermissionId(String permissionId) {
        return permissionRepository.findByPermissionIdAndDelFlag(permissionId, UNDELETED_STATUS);
    }

    /**
     * 根据权限id集合, 查询权限详情列表
     *
     * @param permissionIds
     * @return
     */
    @Override
    public List<Permission> findAllByPermissionIds(List<String> permissionIds) {
        return permissionRepository.findAllByPermissionIdInAndDelFlag(permissionIds, UNDELETED_STATUS);
    }

    /**
     * 根据权限name, 查询权限节点详情
     *
     * @param permissionName
     * @return
     */
    @Override
    public Permission findByPermissionName(String permissionName) {
        return permissionRepository.findByPermissionNameAndDelFlag(permissionName, UNDELETED_STATUS);
    }

    /**
     * 保存权限节点
     *
     * @param permission
     * @return
     */
    public Permission saveOne(Permission permission) {
        if (StringUtils.isBlank(permission.getPermissionId())) {
            permission.setPermissionId(UUID.randomUUID().toString());
        }
        return permissionRepository.save(permission);
    }

    /**
     * 保存权限节点
     *
     * @param permissions
     * @return
     */
    @Override
    public List<Permission> saveAll(List<Permission> permissions) {
        return permissionRepository.saveAll(permissions);
    }

}
