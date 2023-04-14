package com.interview.vatcalculator.web;

import com.interview.vatcalculator.service.VatCalculatorService;
import com.interview.vatcalculator.service.model.Amounts;
import com.interview.vatcalculator.validation.VatCalculatorRequestValidator;
import com.interview.vatcalculator.validation.exception.ValidationException;
import com.interview.vatcalculator.validation.model.Error;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VatCalculatorControllerTest {

    private static final String AMOUNT = "0";
    private static final Double PARSED_AMOUNT = 0d;
    private static final VatCalculatorRequest REQUEST = new VatCalculatorRequest(AMOUNT, AMOUNT, AMOUNT, AMOUNT);
    private static final String ERROR_CODE = "error code";
    private static final String ERROR_MESSAGE = "error message";

    @Mock
    private VatCalculatorRequestValidator validator;

    @Mock
    private VatCalculatorService service;

    @InjectMocks
    private VatCalculatorController controller;

    @Test
    void should_return_successful_response() {

        var expectedHttpStatusCode = HttpStatus.OK;
        var expectedAmounts = new Amounts(PARSED_AMOUNT, PARSED_AMOUNT, PARSED_AMOUNT);

        when(service.calculateVat(PARSED_AMOUNT, PARSED_AMOUNT, PARSED_AMOUNT, PARSED_AMOUNT))
                .thenReturn(expectedAmounts);

        var actual = controller.calculateAmounts(REQUEST);
        assertThat(actual.getStatusCode()).isEqualTo(expectedHttpStatusCode);

        var actualAmounts = actual.getBody().getAmounts();
        assertThat(actualAmounts).isEqualTo(expectedAmounts);
    }

    @Test
    void should_return_error_response() {

        doThrow(new ValidationException(new Error(ERROR_CODE, ERROR_MESSAGE))).when(validator).validate(REQUEST);

        var expectedHttpStatusCode = HttpStatus.BAD_REQUEST;
        var expectedError = new Error(ERROR_CODE, ERROR_MESSAGE);

        var actual = controller.calculateAmounts(REQUEST);
        assertThat(actual.getStatusCode()).isEqualTo(expectedHttpStatusCode);

        var actualError = actual.getBody().getError();
        assertThat(actualError).isEqualTo(expectedError);
    }
}

