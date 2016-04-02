package com.lastminute.service;

public enum InfantPrices {
    IB(10.0),
    BA(15.0),
    LH(7.0),
    FR(20.0),
    VY(10.0),
    TK(5.0),
    U2(19.90);

    private double value;

    InfantPrices(double value) {
        this.value = value;
    }

    double getValue(){
       return this.value;
    }
}
