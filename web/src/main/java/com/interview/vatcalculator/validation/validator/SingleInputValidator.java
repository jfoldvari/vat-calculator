package com.interview.vatcalculator.validation.validator;

import com.interview.vatcalculator.validation.constraint.SingleInputConstraint;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class SingleInputValidator implements ConstraintValidator<SingleInputConstraint, VatCalculatorRequest> {

    @Override
    public boolean isValid(VatCalculatorRequest vatCalculatorRequest, ConstraintValidatorContext context) {
        var isNetProvided = Objects.nonNull(vatCalculatorRequest.getNet());
        var isVatProvided = Objects.nonNull(vatCalculatorRequest.getVat());
        var isGrossProvided = Objects.nonNull(vatCalculatorRequest.getGross());

        return (isNetProvided ^ isVatProvided ^ isGrossProvided)
                ^ (isNetProvided && isVatProvided && isGrossProvided);
    }
}
