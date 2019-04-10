package com.supermarket;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private String name;
    private Product product;
    private Location location;
    private Customer customer;
    private LocalDate data;


    public Order(String name, Product product, Location location, Customer customer, LocalDate data) {
        this.name = name;
        this.product = product;
        this.location = location;
        this.customer = customer;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public Location getLocation() {
        return location;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return getCustomer().getCustomerName();
    }


}
