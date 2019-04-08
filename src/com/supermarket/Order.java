package com.supermarket;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String check;
    private String name;
    private String product;
    private int amount;
    private Date data;


    public Order(String check, String name, String product, int amount, Date data) {
        this.check = check;
        this.name = name;
        this.product = product;
        this.amount = amount;
        this.data = data;
    }


}
