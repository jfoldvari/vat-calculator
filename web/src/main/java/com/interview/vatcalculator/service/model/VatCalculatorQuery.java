package com.interview.vatcalculator.service.model;

public class VatCalculatorQuery {

    private Double net;

    private Double vat;

    private Double gross;

    private Double rate;

    public VatCalculatorQuery(Double net, Double vat, Double gross, Double rate) {
        this.net = net;
        this.vat = vat;
        this.gross = gross;
        this.rate = rate;
    }

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
