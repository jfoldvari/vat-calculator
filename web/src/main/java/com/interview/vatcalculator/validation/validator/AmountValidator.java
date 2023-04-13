package com.interview.vatcalculator.validation.validator;

import com.interview.vatcalculator.validation.constraint.AmountConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class AmountValidator implements ConstraintValidator<AmountConstraint, String> {

    @Override
    public boolean isValid(String vatRate, ConstraintValidatorContext context) {
        return Objects.isNull(vatRate)
                || (NumberUtils.isParsable(vatRate) && NumberUtils.toDouble(vatRate) != 0d);
    }
}
