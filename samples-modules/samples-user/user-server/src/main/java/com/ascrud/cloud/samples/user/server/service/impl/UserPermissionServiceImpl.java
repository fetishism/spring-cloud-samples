package com.ascrud.cloud.samples.user.server.service.impl;

import com.ascrud.cloud.samples.user.server.repository.UserPermissionRepository;
import com.ascrud.cloud.samples.user.server.entity.UserPermission;
import com.ascrud.cloud.samples.user.server.service.UserPermissionService;
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
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    /**
     * 查询用户权限关系列表
     *
     * @return
     */
    @Override
    public List<UserPermission> findAll() {
        return userPermissionRepository.findAllByDelFlag(UNDELETED_STATUS);
    }

    /**
     * 根据用户权限关系id, 查询用户权限关系详情
     *
     * @param userPermissionId 用户权限id
     * @return
     */
    @Override
    public UserPermission findByUserPermissionId(String userPermissionId) {
        return userPermissionRepository.findByUserPermissionIdAndDelFlag(userPermissionId, UNDELETED_STATUS);
    }

    /**
     * 根据用户id, 查询该用户对应的角色权限关系
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<UserPermission> findAllByUserId(String userId) {
        return userPermissionRepository.findAllByUserIdAndDelFlag(userId, UNDELETED_STATUS);
    }

    /**
     * 保存用户权限关系
     *
     * @param userPermission
     * @return
     */
    @Override
    public UserPermission saveOne(UserPermission userPermission) {
        if (StringUtils.isBlank(userPermission.getUserPermissionId())) {
            userPermission.setUserPermissionId(UUID.randomUUID().toString());
        }
        return userPermissionRepository.save(userPermission);
    }

    /**
     * 保存用户权限关系
     *
     * @param userPermissionList
     * @return
     */
    @Override
    public List<UserPermission> saveAll(List<UserPermission> userPermissionList) {
        return userPermissionRepository.saveAll(userPermissionList);
    }
}
