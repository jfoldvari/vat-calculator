package com.interview.vatcalculator.validation.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class VatRateValidatorTest {

    private VatRateValidator validator;

    @BeforeEach
    void setUp() {
        validator = new VatRateValidator();
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Test_Scenarios {
        @ParameterizedTest
        @MethodSource("scenarios")
        void should_return_expected_outcome(String vatRate, boolean expected) {
            var actual = validator.isValid(vatRate, null);

            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> scenarios() {
            return Stream.of(
                    Arguments.of(null, false),
                    Arguments.of("string", false),
                    Arguments.of("15d", false),
                    Arguments.of("0", false),
                    Arguments.of("0.00", false),
                    Arguments.of("7", false),
                    Arguments.of("10", true),
                    Arguments.of("10.00", true),
                    Arguments.of("13", true),
                    Arguments.of("20", true)
            );
        }
    }
}
