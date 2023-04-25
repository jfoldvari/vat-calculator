package com.interview.vatcalculator.validation;

public enum VatCalculatorError {

    MISSING_OR_INVALID_AMOUNT_ERROR("001", "Missing or invalid (0 or non-numeric) amount."),
    ZERO_OR_MORE_THAN_ONE_INPUT_ERROR("002", "Zero or more than one input. Either net, VAT or gross amount has to be provided."),
    MISSING_OR_INVALID_VAT_RATE_ERROR("003", "Missing or invalid VAT rate. Valid VAT rates are 10, 13 and 20 percent."),
    GENERIC_INPUT_ERROR("004", "Generic input error");

    private final String code;
    private final String message;

    VatCalculatorError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
