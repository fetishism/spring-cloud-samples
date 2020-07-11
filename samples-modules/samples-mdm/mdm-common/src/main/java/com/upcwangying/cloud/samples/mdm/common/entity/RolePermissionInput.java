package com.ascrud.cloud.samples.mdm.common.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色权限关系表
 *
 * @author walkman
 */
public class RolePermissionInput implements Serializable {
    private static final long serialVersionUID = 5235051371744724134L;

    /**
     * 角色Id
     */
    @NotBlank(message = "roleId不允许为空")
    private String roleId;

    /**
     * 权限Id
     */
    @NotBlank(message = "permissionId不允许为空")
    private String permissionId;

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
        return "RolePermissionInput{" +
                "roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}
