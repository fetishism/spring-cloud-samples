package com.ascrud.cloud.samples.user.common.entity;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色表
 *
 * @author walkman
 */
public class RoleInput implements Serializable {
    private static final long serialVersionUID = 3594192868028987705L;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名字不允许为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "RoleInput{" +
                "roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
