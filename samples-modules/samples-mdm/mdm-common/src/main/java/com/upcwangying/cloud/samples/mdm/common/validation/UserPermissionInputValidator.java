package com.ascrud.cloud.samples.mdm.common.validation;


import com.ascrud.cloud.samples.mdm.common.validation.annotation.UserPermissionInputAnno;
import com.ascrud.cloud.samples.mdm.common.entity.UserPermissionInput;
import com.ascrud.cloud.samples.mdm.common.enums.UserPermissionType;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author walkman
 */
public class UserPermissionInputValidator implements ConstraintValidator<UserPermissionInputAnno, UserPermissionInput> {

    @Override
    public void initialize(UserPermissionInputAnno constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserPermissionInput value, ConstraintValidatorContext context) {
        String type = value.getType();
        String roleId = value.getRoleId();
        String permissionId = value.getPermissionId();

        if (StringUtils.isEmpty(type) || !UserPermissionType.isValid(type)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{com.ascrud.cloud.samples.user.common.validation.annotation.UserPermissionInput.type.message}").addConstraintViolation();
            return false;
        }

        // 如果type为role, 且roleId为空
        if (UserPermissionType.role.name().equals(type) && StringUtils.isEmpty(roleId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{com.ascrud.cloud.samples.user.common.validation.annotation.UserPermissionInput.role.message}").addConstraintViolation();
            return false;
        }

        // 如果type为permission, 且permissionId为空
        if (UserPermissionType.permission.name().equals(type) && StringUtils.isEmpty(permissionId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{com.ascrud.cloud.samples.user.common.validation.annotation.UserPermissionInput.permission.message}").addConstraintViolation();
            return false;
        }

        return true;

    }

}
