package com.ascrud.cloud.samples.core.validation;

import com.ascrud.cloud.samples.core.validation.constraints.StringMinLength;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 * @author walkman
 */
public class StringMinLengthValidator implements ConstraintValidator<StringMinLength, String> {
    private long strMinLen;

    @Override
    public void initialize(StringMinLength constraintAnnotation) {
        strMinLen = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !(StringUtils.hasLength(value) && value.length() < strMinLen);
    }
}
