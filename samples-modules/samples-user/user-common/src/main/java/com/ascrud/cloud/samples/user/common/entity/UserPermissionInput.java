package com.ascrud.cloud.samples.user.common.entity;

import com.ascrud.cloud.samples.user.common.validation.annotation.UserPermissionInputAnno;

import java.io.Serializable;

/**
 *
 *
 * @author walkman
 */
@UserPermissionInputAnno
public class UserPermissionInput implements Serializable {
    private static final long serialVersionUID = -6632145499985950477L;

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
        return "UserPermissionInput{" +
                "roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
