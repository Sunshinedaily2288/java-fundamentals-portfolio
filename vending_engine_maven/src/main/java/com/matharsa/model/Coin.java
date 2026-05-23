package com.matharsa.model;

public enum Coin {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
