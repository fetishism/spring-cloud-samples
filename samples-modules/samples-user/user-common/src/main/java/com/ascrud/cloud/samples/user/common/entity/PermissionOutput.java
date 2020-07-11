package com.ascrud.cloud.samples.user.common.entity;

import java.io.Serializable;

/**
 * 权限表
 *
 * @author walkman
 */
public class PermissionOutput implements Serializable {
    private static final long serialVersionUID = 5344579694978611470L;

    /**
     * 权限Id
     */
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

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
}
