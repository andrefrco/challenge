package com.contaazul.bankslips.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UUIDValidator implements ConstraintValidator<UUID, String> {

    private static final Pattern PATTERN =
            Pattern.compile( "^[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}$" );

    @Override
    public void initialize(UUID constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return PATTERN.matcher( value ).matches();
    }
}
