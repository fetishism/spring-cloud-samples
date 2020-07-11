package com.ascrud.cloud.samples.mdm.common.entity;

import java.io.Serializable;

/**
 * 角色权限关系表
 *
 * @author walkman
 */
public class RolePermissionOutput implements Serializable {
    private static final long serialVersionUID = -6170823454794035343L;

    /**
     * 主键
     */
    private String rolePermissionId;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 权限Id
     */
    private String permissionId;

    public String getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(String rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
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

    @Override
    public String toString() {
        return "RolePermissionOutput{" +
                "rolePermissionId='" + rolePermissionId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}
