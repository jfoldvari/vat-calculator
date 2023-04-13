package com.interview.vatcalculator.web.api;


import com.interview.vatcalculator.validation.constraint.SingleInputConstraint;
import com.interview.vatcalculator.validation.constraint.AmountConstraint;
import com.interview.vatcalculator.validation.constraint.VatRateConstraint;
import io.swagger.v3.oas.annotations.Parameter;

@SingleInputConstraint
public class VatCalculatorRequest {

    @Parameter
    @AmountConstraint
    private String net;

    @Parameter
    @AmountConstraint
    private String vat;

    @Parameter
    @AmountConstraint
    private String gross;

    @Parameter
    @VatRateConstraint
    private String rate;

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
