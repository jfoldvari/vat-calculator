package com.interview.vatcalculator.service;

import com.interview.vatcalculator.web.api.VatCalculatorResponse;
import com.interview.vatcalculator.validation.VatCalculatorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.interview.vatcalculator.service.utils.RoundUtils.round;

@Component
public class VatCalculatorService {

    @Autowired
    private VatCalculatorValidator validator;

    public VatCalculatorResponse calculateVat(Double net, Double vat, Double gross, Double rate) {

        VatCalculatorResponse response = null;
        if (Optional.ofNullable(net).isPresent()) {
            response = calculateVatAndGross(net, rate);
        } else if (Optional.ofNullable(vat).isPresent()) {
            response = calculateNetAndGross(vat, rate);
        } else if (Optional.ofNullable(gross).isPresent()) {
            response = calculateNetAndVat(gross, rate);
        }

        return response;
    }

    private VatCalculatorResponse calculateVatAndGross(Double net, Double rate) {
        var vat = calculateVatFromNet(net, rate);
        return new VatCalculatorResponse(net,
                vat,
                calculateGrossFromVat(vat, rate));
    }

    private VatCalculatorResponse calculateNetAndGross(Double vat, Double rate) {
        return new VatCalculatorResponse(calculateNetFromVat(vat, rate),
                vat,
                calculateGrossFromVat(vat, rate));
    }

    private VatCalculatorResponse calculateNetAndVat(Double gross, Double rate) {
        var net = calculateNetFromGross(gross, rate);
        return new VatCalculatorResponse(net,
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
