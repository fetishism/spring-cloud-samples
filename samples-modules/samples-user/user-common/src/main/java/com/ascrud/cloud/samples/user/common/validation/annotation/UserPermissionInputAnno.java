package com.ascrud.cloud.samples.user.common.validation.annotation;

import com.ascrud.cloud.samples.user.common.validation.UserPermissionInputValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 *
 * @author walkman
 */
@Documented
@Constraint(validatedBy = { UserPermissionInputValidator.class })
@Target({ TYPE_USE })
@Retention(RUNTIME)
public @interface UserPermissionInputAnno {

    String message() default "{com.ascrud.cloud.samples.user.common.validation.annotation.UserPermissionInput.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
