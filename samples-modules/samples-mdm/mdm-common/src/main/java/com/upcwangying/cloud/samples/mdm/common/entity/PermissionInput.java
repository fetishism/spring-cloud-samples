package com.ascrud.cloud.samples.mdm.common.entity;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 权限表
 *
 * @author walkman
 */
public class PermissionInput implements Serializable {
    private static final long serialVersionUID = 218316083242080362L;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 权限名
     */
    @NotBlank(message = "权限名字不允许为空")
    private String permissionName;

    /**
     * 权限点
     */
    private String permissionPath;

    /**
     * 请求方法
     */
    @NotBlank(message = "请求方法不允许为空")
    private String requestMethod;

    /**
     * 权限描述
     */
    private String permissionDesc;

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

    @Override
    public String toString() {
        return "PermissionInput{" +
                "parentId='" + parentId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionPath='" + permissionPath + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", permissionDesc='" + permissionDesc + '\'' +
                '}';
    }
}
