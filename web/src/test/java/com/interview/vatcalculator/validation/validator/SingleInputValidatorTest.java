package com.interview.vatcalculator.validation.validator;

import com.interview.vatcalculator.web.api.VatCalculatorRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SingleInputValidatorTest {

    private SingleInputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new SingleInputValidator();
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Test_Scenarios {
        @ParameterizedTest
        @MethodSource("scenarios")
        void should_return_expected_outcome(VatCalculatorRequest request, boolean expected) {
            var actual = validator.isValid(request, null);

            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> scenarios() {
            return Stream.of(
                    Arguments.of(createVatCalculatorRequest(null, null, null), false),
                    Arguments.of(createVatCalculatorRequest("5.0", "10", null), false),
                    Arguments.of(createVatCalculatorRequest("5.0", "10", "20"), false),
                    Arguments.of(createVatCalculatorRequest("5.0", null, "30"), false),
                    Arguments.of(createVatCalculatorRequest(null, "10", "40"), false),
                    Arguments.of(createVatCalculatorRequest("5.0", null, null), true),
                    Arguments.of(createVatCalculatorRequest(null, "10.0", null), true),
                    Arguments.of(createVatCalculatorRequest(null, null, "30.0"), true)
            );
        }

        private static VatCalculatorRequest createVatCalculatorRequest(String net, String vat, String gross) {
            return new VatCalculatorRequest(net, vat, gross, null);
        }
    }
}
