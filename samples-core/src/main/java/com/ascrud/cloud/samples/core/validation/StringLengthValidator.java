package com.ascrud.cloud.samples.core.validation;

import com.ascrud.cloud.samples.core.validation.constraints.StringLength;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author walkman
 */
public class StringLengthValidator implements ConstraintValidator<StringLength, String> {
    private long strMinLen;
    private long strMaxLen;

    @Override
    public void initialize(StringLength constraintAnnotation) {
        strMinLen = constraintAnnotation.min();
        strMaxLen = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.hasLength(value) && value.length() < strMinLen) {
            return false;
        }

        if (StringUtils.hasLength(value) && value.length() > strMaxLen) {
            return false;
        }
        return true;
    }
}
