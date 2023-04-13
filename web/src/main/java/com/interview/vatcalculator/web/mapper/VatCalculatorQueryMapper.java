package com.interview.vatcalculator.web.mapper;

import com.interview.vatcalculator.service.model.VatCalculatorQuery;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

public class VatCalculatorQueryMapper {
    public static VatCalculatorQuery toVatCalculatorQuery(VatCalculatorRequest request) {
        return new VatCalculatorQuery(
                Optional.ofNullable(request.getNet())
                        .map(NumberUtils::toDouble)
                        .orElse(null),
                Optional.ofNullable(request.getVat())
                        .map(NumberUtils::toDouble)
                        .orElse(null),
                Optional.ofNullable(request.getGross())
                        .map(NumberUtils::toDouble)
                        .orElse(null),
                Optional.ofNullable(request.getRate())
                        .map(NumberUtils::toDouble)
                        .orElse(null));
    }
}
