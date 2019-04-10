package com.supermarket;

public class Product {

    private String product;
    private int amount;
    private int coast;

    public Product(String product, int amount, int coast) {
        this.product = product;
        this.amount = amount;
        this.coast = coast;
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
}
