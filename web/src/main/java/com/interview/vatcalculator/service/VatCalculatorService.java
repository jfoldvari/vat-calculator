package com.interview.vatcalculator.service;

import com.interview.vatcalculator.service.model.Amounts;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.interview.vatcalculator.service.utils.RoundUtils.round;

@Component
public class VatCalculatorService {

    public Amounts calculateVat(Double net, Double vat, Double gross, Double rate) {

        Amounts amounts = null;
        if (Optional.ofNullable(net).isPresent()) {
            amounts = calculateVatAndGross(net, rate);
        } else if (Optional.ofNullable(vat).isPresent()) {
            amounts = calculateNetAndGross(vat, rate);
        } else if (Optional.ofNullable(gross).isPresent()) {
            amounts = calculateNetAndVat(gross, rate);
        }

        return amounts;
    }

    private Amounts calculateVatAndGross(Double net, Double rate) {
        var vat = calculateVatFromNet(net, rate);
        return new Amounts(net,
                vat,
                calculateGrossFromVat(vat, rate));
    }

    private Amounts calculateNetAndGross(Double vat, Double rate) {
        return new Amounts(calculateNetFromVat(vat, rate),
                vat,
                calculateGrossFromVat(vat, rate));
    }

    private Amounts calculateNetAndVat(Double gross, Double rate) {
        var net = calculateNetFromGross(gross, rate);
        return new Amounts(net,
                calculateVatFromNet(net, rate),
                gross);
    }

    private Double calculateVatFromNet(Double net, Double rate) {
        return round((net * rate) / 100);
    }

    private Double calculateNetFromVat(Double vat, Double rate) {
        return round(vat / (rate / 100));
    }

    private Double calculateGrossFromVat(Double vat, Double rate) {
        return round(vat / (rate / 100) + vat);
    }

    private Double calculateNetFromGross(Double gross, Double rate) {
        return round(gross / (1 + rate / 100));
    }
}
