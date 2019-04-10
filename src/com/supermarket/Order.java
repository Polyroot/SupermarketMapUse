package com.supermarket;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private String checkNumber;
    private String name;
    private String product;
    private int amount;
    private int coast;
    private String locate;
    private String coordinate;
    private String locateDiscr;
    private LocalDate data;

    public Order(String checkNumber, String name, String product, int amount, int coast, String locate, String coordinate, String locateDiscr, LocalDate data) {
        this.checkNumber = checkNumber;
        this.name = name;
        this.product = product;
        this.amount = amount;
        this.coast = coast;
        this.locate = locate;
        this.coordinate = coordinate;
        this.locateDiscr = locateDiscr;
        this.data = data;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getCoast() {
        return coast;
    }

    public String getLocate() {
        return locate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public String getLocateDiscr() {
        return locateDiscr;
    }

    public LocalDate getData() {
        return data;
    }


    @Override
    public String toString() {
        return getCheckNumber();
    }


}
