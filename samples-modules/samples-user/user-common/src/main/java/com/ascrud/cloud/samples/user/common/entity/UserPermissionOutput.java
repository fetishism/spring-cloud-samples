package com.ascrud.cloud.samples.user.common.entity;

import java.io.Serializable;

/**
 *
 *
 * @author walkman
 */
public class UserPermissionOutput implements Serializable {
    private static final long serialVersionUID = 2612229197950796288L;

    /**
     * 主键
     */
    private String UserPermissionId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 权限Id
     */
    private String permissionId;

    /**
     * 数据类型: role, permission
     */
    private String type;

    public String getUserPermissionId() {
        return UserPermissionId;
    }

    public void setUserPermissionId(String userPermissionId) {
        UserPermissionId = userPermissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserPermissionOutput{" +
                "UserPermissionId='" + UserPermissionId + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
