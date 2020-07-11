package com.ascrud.cloud.samples.core.validation;

import com.ascrud.cloud.samples.core.validation.constraints.StringMaxLength;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author walkman
 */
public class StringMaxLengthValidator implements ConstraintValidator<StringMaxLength, String> {
    private long strMaxLen;

    @Override
    public void initialize(StringMaxLength constraintAnnotation) {
        strMaxLen = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !(StringUtils.hasLength(value) && value.length() > strMaxLen);
    }
}
