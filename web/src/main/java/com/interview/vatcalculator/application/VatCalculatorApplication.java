package com.interview.vatcalculator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.interview.vatcalculator")
public class VatCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VatCalculatorApplication.class, args);
    }
}
