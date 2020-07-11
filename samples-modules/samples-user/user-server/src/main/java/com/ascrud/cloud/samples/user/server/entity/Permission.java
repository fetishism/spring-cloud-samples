package com.ascrud.cloud.samples.user.server.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 权限表
 *
 * @author walkman
 */
@Data
@Entity
@ToString
public class Permission implements Serializable {
    private static final long serialVersionUID = 547515537777310241L;

    /**
     * 权限Id
     */
    @Id
    private String permissionId;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 权限点
     */
    private String permissionPath;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 删除标识
     */
    private char delFlag;
}
