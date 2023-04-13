package com.interview.vatcalculator.validation.validator;

import com.interview.vatcalculator.validation.constraint.VatRateConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Objects;


public class VatRateValidator implements ConstraintValidator<VatRateConstraint, String> {

    List<Double> VALID_VAT_RATES = List.of(10d, 13d, 20d);

    @Override
    public boolean isValid(String vatRate, ConstraintValidatorContext context) {
        return !Objects.isNull(vatRate)
                && NumberUtils.isParsable(vatRate)
                && VALID_VAT_RATES.contains(NumberUtils.toDouble(vatRate));
    }
}
