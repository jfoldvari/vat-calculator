package com.interview.vatcalculator.web.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.interview.vatcalculator.service.model.Amounts;
import com.interview.vatcalculator.validation.model.Error;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VatCalculatorResponse {

    private Amounts amounts;

    private Error error;

    public VatCalculatorResponse(Amounts amounts) {
        this.amounts = amounts;
    }

    public VatCalculatorResponse(Error error) {
        this.error = error;
    }

    public Amounts getAmounts() {
        return amounts;
    }

    public Error getError() {
        return error;
    }
}
