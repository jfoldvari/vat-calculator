package com.interview.vatcalculator.validation;

import com.interview.vatcalculator.validation.constraint.AmountConstraint;
import com.interview.vatcalculator.validation.constraint.SingleInputConstraint;
import com.interview.vatcalculator.validation.constraint.VatRateConstraint;
import com.interview.vatcalculator.validation.exception.ValidationException;
import com.interview.vatcalculator.validation.model.Error;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@Component
public class VatCalculatorRequestValidator {

    @Autowired
    private Validator validator;

    private final Map<Class<? extends Annotation>, VatCalculatorError> constraintsToErrors = Map.of(
            SingleInputConstraint.class, VatCalculatorError.ZERO_OR_MORE_THAN_ONE_INPUT_ERROR,
            AmountConstraint.class, VatCalculatorError.MISSING_OR_INVALID_AMOUNT_ERROR,
            VatRateConstraint.class, VatCalculatorError.MISSING_OR_INVALID_VAT_RATE_ERROR
    );

    public void validate(VatCalculatorRequest request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw fromViolations(violations);
        }
    }

    public ValidationException fromViolations(Set<ConstraintViolation<VatCalculatorRequest>> violations) {
        return violations.stream()
                .map(this::fromViolation)
                .findFirst()
                .map(error -> new ValidationException(new Error(error.getCode(), error.getMessage())))
                .orElse(null);
    }

    private VatCalculatorError fromViolation(ConstraintViolation<VatCalculatorRequest> violation) {
        return Optional.ofNullable(constraintsToErrors
                        .get(violation.getConstraintDescriptor().getAnnotation().annotationType()))
                .orElse(VatCalculatorError.GENERIC_INPUT_ERROR);
    }
}
