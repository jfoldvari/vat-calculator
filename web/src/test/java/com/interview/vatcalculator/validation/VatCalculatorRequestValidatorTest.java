package com.interview.vatcalculator.validation;

import com.interview.vatcalculator.validation.constraint.SingleInputConstraint;
import com.interview.vatcalculator.validation.exception.ValidationException;
import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VatCalculatorRequestValidatorTest {

    private static final VatCalculatorRequest REQUEST_TO_VALIDATE = new VatCalculatorRequest();

    private static final String ERROR_CODE = "002";
    private static final String ERROR_MESSAGE = "Zero or more than one input. Either net, VAT or gross amount has to be provided.";

    @Mock
    private Validator validator;

    @Mock
    private ConstraintViolation constraintViolation;

    @Mock
    private ConstraintDescriptor constraintDescriptor;

    @Mock
    private Annotation secondAnnotation;

    @InjectMocks
    private VatCalculatorRequestValidator vatCalculatorRequestValidator;

    @Test
    void should_not_throw_validation_exception_when_no_constraint_violation_exists() {

        when(validator.validate(REQUEST_TO_VALIDATE)).thenReturn(Set.of());

        assertDoesNotThrow(() -> validator.validate(REQUEST_TO_VALIDATE));
    }

    @Test
    void should_throw_validation_exception_when_constraint_violation_exists() {

        when(constraintViolation.getConstraintDescriptor()).thenReturn(constraintDescriptor);
        when(constraintDescriptor.getAnnotation()).thenReturn(secondAnnotation);
        doReturn(SingleInputConstraint.class).when(secondAnnotation).annotationType();

        when(validator.validate(REQUEST_TO_VALIDATE)).thenReturn(Set.of(constraintViolation));

        ValidationException exception =
                assertThrows(ValidationException.class, () -> vatCalculatorRequestValidator.validate(REQUEST_TO_VALIDATE));

        assertThat(exception.getError().getCode()).isEqualTo(ERROR_CODE);
        assertThat(exception.getError().getMessage()).isEqualTo(ERROR_MESSAGE);
    }
}
