package com.interview.vatcalculator.web.api;


public class VatCalculatorResponse {

    private final Double net;
    private final Double vat;
    private final Double gross;

    public VatCalculatorResponse(Double net, Double vat, Double gross) {
        this.net = net;
        this.vat = vat;
        this.gross = gross;
    }

    public Double getNet() {
        return net;
    }

    public Double getVat() {
        return vat;
    }

    public Double getGross() {
        return gross;
    }

}
