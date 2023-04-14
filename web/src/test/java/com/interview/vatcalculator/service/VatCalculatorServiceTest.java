package com.interview.vatcalculator.service;

import com.interview.vatcalculator.service.model.Amounts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class VatCalculatorServiceTest {

    private VatCalculatorService service;

    @BeforeEach
    void setUp() {
        service = new VatCalculatorService();
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Test_Scenarios {
        @ParameterizedTest
        @MethodSource("scenarios")
        void should_return_expected_outcome(Double net, Double vat, Double gross, Double rate, Amounts expected) {
            var actual = service.calculateVat(net, vat, gross, rate);

            Assertions.assertThat(actual).isEqualTo(expected);
        }

        Stream<Arguments> scenarios() {
            return Stream.of(
                    Arguments.of(null, null, null, null, null),

                    Arguments.of(100d, null, null, 10d, new Amounts(100d, 10d, 110d)),
                    Arguments.of(100d, null, null, 13d, new Amounts(100d, 13d, 113d)),
                    Arguments.of(123.5d, null, null, 20d, new Amounts(123.5d, 24.7d, 148.2d)),

                    Arguments.of(null, 10d, null, 10d, new Amounts(100d, 10d, 110d)),
                    Arguments.of(null, 13d, null, 13d, new Amounts(100d, 13d, 113d)),
                    Arguments.of(null, 16.78d, null, 20d, new Amounts(83.9d, 16.78d, 100.68d)),

                    Arguments.of(null, null, 110d, 10d, new Amounts(100d, 10d, 110d)),
                    Arguments.of(null, null, 113d, 13d, new Amounts(100d, 13d, 113d)),
                    Arguments.of(null, null, 156.98d, 20d, new Amounts(130.82d, 26.16d, 156.98d))
            );
        }
    }
}
