package com.interview.vatcalculator.validation.constraint;


import com.interview.vatcalculator.validation.validator.SingleInputValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SingleInputValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleInputConstraint {
    String message() default "More than one input. Either net, VAT or gross amount has to be provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}