package com.sitco.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( ElementType.FIELD)
@Retention( RetentionPolicy.RUNTIME )
@Constraint(validatedBy = UppercaseValidator.class)
public @interface Uppercase {
    String message() default "must be uppercase";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
