package com.ascrud.cloud.samples.user.server.utils;

import com.ascrud.cloud.samples.user.server.entity.Permission;
import com.ascrud.cloud.samples.user.server.entity.Role;
import com.ascrud.cloud.samples.user.server.entity.RolePermission;
import com.ascrud.cloud.samples.user.server.entity.User;
import com.ascrud.cloud.samples.user.server.entity.UserPermission;

import static com.ascrud.cloud.samples.core.constant.Constants.UNDELETED_STATUS;
import static org.apache.commons.lang.StringUtils.EMPTY;

/**
 *
 *
 * @author walkman
 */
public final class BeanCreators extends com.ascrud.cloud.samples.user.common.utils.BeanCreators {

    public static User createInitUser() {
        User user = new User();
        user.setUserId(EMPTY);
        user.setDelFlag(UNDELETED_STATUS);
        return user;
    }

    public static Permission createInitPermission() {
        Permission permission = new Permission();
        permission.setPermissionId(EMPTY);
        permission.setDelFlag(UNDELETED_STATUS);
        return permission;
    }

    public static Role createInitRole() {
        Role role = new Role();
        role.setRoleId(EMPTY);
        role.setDelFlag(UNDELETED_STATUS);
        return role;
    }

    public static RolePermission createInitRolePermission() {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionId(EMPTY);
        rolePermission.setDelFlag(UNDELETED_STATUS);
        return rolePermission;
    }

    public static UserPermission createInitUserPermission() {
        UserPermission userPermission = new UserPermission();
        userPermission.setUserPermissionId(EMPTY);
        userPermission.setDelFlag(UNDELETED_STATUS);
        return userPermission;
    }
}
