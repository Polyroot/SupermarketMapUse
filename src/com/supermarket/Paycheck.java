package com.supermarket;

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
        if (!(obj instanceof Paycheck)) return false;
        return paycheckNumber.equals(((Paycheck) obj).getPaycheckNumber());
    }
}
