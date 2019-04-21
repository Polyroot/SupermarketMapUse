package com.supermarket.Objects;

import java.time.LocalDate;

public class Order {

    private String name;
    private Product product;
    private Location location;
    private Customer customer;
    private LocalDate date;

    public Order(String name, Product product, Location location, Customer customer, LocalDate date) {
        this.name = name;
        this.product = product;
        this.location = location;
        this.customer = customer;
        this.date = date;
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
        return date;
    }

    @Override
    public String toString() {
        return getCustomer().getCustomerName();


    }


}
