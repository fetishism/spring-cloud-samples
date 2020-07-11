package com.ascrud.cloud.samples.user.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 角色权限关系表
 *
 * @author walkman
 */
@Data
@Entity
@ToString
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 83865404170243264L;

    /**
     * 主键
     */
    @Id
    private String rolePermissionId;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 权限Id
     */
    private String permissionId;

    /**
     * 删除标识
     */
    private char delFlag;
}
