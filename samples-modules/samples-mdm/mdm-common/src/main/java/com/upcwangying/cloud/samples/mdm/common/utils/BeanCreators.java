package com.ascrud.cloud.samples.mdm.common.utils;

import com.ascrud.cloud.samples.mdm.common.entity.PermissionOutput;
import com.ascrud.cloud.samples.mdm.common.entity.RoleOutput;
import com.ascrud.cloud.samples.mdm.common.entity.RolePermissionOutput;
import com.ascrud.cloud.samples.mdm.common.entity.UserOutput;
import com.ascrud.cloud.samples.mdm.common.entity.UserPermissionOutput;

/**
 *
 *
 * @author walkman
 */
public class BeanCreators {

    public static UserOutput createUserOutput() {
        return new UserOutput();
    }

    public static PermissionOutput createPermissionOutput() {
        return new PermissionOutput();
    }

    public static RoleOutput createRoleOutput() {
        return new RoleOutput();
    }

    public static RolePermissionOutput createRolePermissionOutput() {
        return new RolePermissionOutput();
    }

    public static UserPermissionOutput createUserPermissionOutput() {
        return new UserPermissionOutput();
    }

}
