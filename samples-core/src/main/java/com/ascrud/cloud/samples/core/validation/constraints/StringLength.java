package com.ascrud.cloud.samples.core.validation.constraints;

import com.ascrud.cloud.samples.core.validation.StringLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author walkman
 */
@Documented
@Constraint(validatedBy = {StringLengthValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(StringLength.List.class)
public @interface StringLength {
    String message() default "{com.ascrud.cloud.samples.core.validation.constraints.StringLength.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return size the element must be higher or equal to
     */
    long min() default 0;

    /**
     * @return size the element must be lower or equal to
     */
    long max() default Long.MAX_VALUE;

    /**
     * Defines several {@code @StringLength} constraints on the same element.
     *
     * @see StringLength
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        StringLength[] value();
    }
}
