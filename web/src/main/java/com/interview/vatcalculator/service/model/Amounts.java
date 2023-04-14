package com.interview.vatcalculator.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Amounts {

    private final Double net;

    private final Double vat;

    private final Double gross;

    public Amounts(Double net, Double vat, Double gross) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Amounts amounts = (Amounts) o;

        return new EqualsBuilder().append(net, amounts.net).append(vat, amounts.vat).append(gross, amounts.gross).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(net).append(vat).append(gross).toHashCode();
    }

    @Override
    public String toString() {
        return "Amounts{" +
                "net=" + net +
                ", vat=" + vat +
                ", gross=" + gross +
                '}';
    }
}