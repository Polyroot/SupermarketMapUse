package com.supermarket;

import java.util.Objects;

public class Paycheck {
    private String paycheckNumber;

    public Paycheck(String paycheckNumber) {
        this.paycheckNumber = paycheckNumber;
    }

    public String getPaycheckNumber() {
        return paycheckNumber;
    }

    @Override
    public String toString() {
        return paycheckNumber;
    }

    @Override
    public int hashCode() {
        return paycheckNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
