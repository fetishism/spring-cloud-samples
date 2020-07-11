package com.ascrud.cloud.samples.core.validation.constraints;

import com.ascrud.cloud.samples.core.validation.StringMinLengthValidator;

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
@Constraint(validatedBy = {StringMinLengthValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(StringMinLength.List.class)
public @interface StringMinLength {
    String message() default "{com.ascrud.cloud.samples.core.validation.constraints.StringMinLength.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return value the element must be higher or equal to
     */
    long value();

    /**
     * Defines several {@code @StringMinLength} constraints on the same element.
     *
     * @see StringMinLength
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        StringMinLength[] value();
    }
}
