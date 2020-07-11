package com.ascrud.cloud.samples.user.common.enums;

/**
 *
 *
 * @author walkman
 */
public enum UserPermissionType {

    /**
     * 角色类型
     */
    role,

    /**
     * 权限类型
     */
    permission;

    public static boolean isValid(String name) {
        for (UserPermissionType userStatusEnum : UserPermissionType.values()) {
            if (userStatusEnum.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
