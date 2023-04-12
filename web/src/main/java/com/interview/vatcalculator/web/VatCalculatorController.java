package com.interview.vatcalculator.web;

import com.interview.vatcalculator.web.api.VatCalculatorQuery;
import com.interview.vatcalculator.web.api.VatCalculatorResponse;
import com.interview.vatcalculator.service.VatCalculatorService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VatCalculatorController implements VatCalculatorEndpoint {

    @Autowired
    private VatCalculatorService service;

    @Override
    public VatCalculatorResponse getVatAmounts(@ParameterObject VatCalculatorQuery query) {
        return service.calculateVat(query.getNet(),
                query.getVat(),
                query.getGross(),
                query.getRate());
    }
}
