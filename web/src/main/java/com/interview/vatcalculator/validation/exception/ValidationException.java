package com.interview.vatcalculator.validation.exception;

import com.interview.vatcalculator.validation.model.Error;

public class ValidationException extends RuntimeException {

    private final Error error;

    public ValidationException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
