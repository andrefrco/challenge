package com.contaazul.bankslips.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UUIDValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UUID {

    String message() default "Invalid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
