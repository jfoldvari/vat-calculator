package com.interview.vatcalculator.web;

import com.interview.vatcalculator.service.VatCalculatorService;
import com.interview.vatcalculator.service.model.VatCalculatorQuery;
import com.interview.vatcalculator.validation.VatCalculatorRequestValidator;
import com.interview.vatcalculator.validation.exception.ValidationException;
import com.interview.vatcalculator.validation.model.Error;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import com.interview.vatcalculator.web.api.VatCalculatorResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.interview.vatcalculator.web.mapper.VatCalculatorQueryMapper.toVatCalculatorQuery;

@RestController
public class VatCalculatorController implements VatCalculatorEndpoint {

    @Autowired
    private VatCalculatorRequestValidator validator;

    @Autowired
    private VatCalculatorService service;

    @Override
    public ResponseEntity<VatCalculatorResponse> calculateAmounts(@ParameterObject VatCalculatorRequest request) {

        ResponseEntity<VatCalculatorResponse> response;
        try {
            validator.validate(request);
            var query = toVatCalculatorQuery(request);
            response = new ResponseEntity<>(createSuccessfulResponse(query), HttpStatus.OK);

        } catch (ValidationException ex) {
            response = new ResponseEntity<>(createErrorResponse(ex.getError()), HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    private VatCalculatorResponse createSuccessfulResponse(VatCalculatorQuery query) {
        return new VatCalculatorResponse(
                service.calculateVat(query.getNet(),
                        query.getVat(),
                        query.getGross(),
                        query.getRate()));
    }

    private VatCalculatorResponse createErrorResponse(Error error) {
        return new VatCalculatorResponse(error);
    }
}
