package com.ascrud.cloud.samples.user.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 *
 * @author walkman
 */
@Data
@Entity
@ToString
public class UserPermission implements Serializable {
    private static final long serialVersionUID = -7588876057727552536L;

    /**
     * 主键
     */
    @Id
    private String userPermissionId;

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

    /**
     * 删除标识
     */
    private char delFlag;

}
