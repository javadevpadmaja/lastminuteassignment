package com.lastminute.service;

public enum PassengerType {
    ADULT(100),
    CHILD(33),
    INFANT(0);

    private int value;

    PassengerType(int value) {
        this.value = value;
    }
}
