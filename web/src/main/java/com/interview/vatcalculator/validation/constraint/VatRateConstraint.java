package com.interview.vatcalculator.validation.constraint;


import com.interview.vatcalculator.validation.validator.VatRateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VatRateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VatRateConstraint {
    String message() default "Missing or invalid VAT rate. Valid VAT rates are 10, 13 and 20 percent.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}