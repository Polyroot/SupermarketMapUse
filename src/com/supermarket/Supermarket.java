package com.supermarket;

import java.util.HashMap;

public class Supermarket {


    //HashMap<Customer, Order> map = new HashMap<Customer,Order>();


    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        supermarket.go();
    }


    public void go(){

        ParserChecks.printChecks("log.txt");

    }



}
