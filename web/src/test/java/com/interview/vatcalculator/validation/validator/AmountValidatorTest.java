package com.interview.vatcalculator.validation.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class AmountValidatorTest {

    private AmountValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AmountValidator();
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Test_Scenarios {
        @ParameterizedTest
        @MethodSource("scenarios")
        void should_return_expected_outcome(String amount, boolean expected) {
            var actual = validator.isValid(amount, null);

            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> scenarios() {
            return Stream.of(
                    Arguments.of("string", false),
                    Arguments.of("15L", false),
                    Arguments.of("0", false),
                    Arguments.of("0.00", false),
                    Arguments.of(null, true),
                    Arguments.of("1", true),
                    Arguments.of("1.23", true)
            );
        }
    }
}
