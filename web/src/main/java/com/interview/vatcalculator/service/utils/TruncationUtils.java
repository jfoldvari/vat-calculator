package com.interview.vatcalculator.service.utils;

public class TruncationUtils {

    private static final int DEFAULT_FRACTIONAL_DIGITS = 2;

    public static double truncate(double value) {
        double scale = Math.pow(10, DEFAULT_FRACTIONAL_DIGITS);
        return Math.round(value * scale) / scale;
    }
}
