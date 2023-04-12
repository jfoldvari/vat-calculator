package com.interview.vatcalculator.web.api;

import io.swagger.v3.oas.annotations.Parameter;

public class VatCalculatorQuery {

    @Parameter
    private Double net;
    @Parameter
    private Double vat;
    @Parameter
    private Double gross;
    @Parameter
    private Double rate;

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
