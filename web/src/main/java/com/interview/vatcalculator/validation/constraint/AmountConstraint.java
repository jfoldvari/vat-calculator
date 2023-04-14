package com.interview.vatcalculator.validation.constraint;


import com.interview.vatcalculator.validation.validator.AmountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AmountValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountConstraint {
    String message() default "Missing or invalid amount.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}