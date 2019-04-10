package com.supermarket;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Supermarket {


    HashMap<Customer, Order> map = new HashMap<Customer,Order>();


    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        supermarket.go();
    }

    public void go(){

        for (int i = 0; i<ParserLogFileChecks.getChecks("log.txt").size(); i++){

            ArrayList<String[]> listChecks = ParserLogFileChecks.getChecks("log.txt").get(i);

            String checkNumber = listChecks.get(0)[0];
            String customerName = listChecks.get(1)[0];
            String phoneNumber = listChecks.get(1)[1];
            String name = listChecks.get(2)[0];
            String product = listChecks.get(3)[0];
            int amount = Integer.parseInt(listChecks.get(3)[1].replaceAll("\\D+", ""));
            int coast = Integer.parseInt(listChecks.get(3)[2].replaceAll("\\D+", ""));
            String locate = listChecks.get(4)[0];
            String coordinate = listChecks.get(4)[1];
            String locateDiscr = listChecks.get(4)[2];
            LocalDate data = LocalDate.parse(listChecks.get(5)[0], DateTimeFormatter.ofPattern("dd.MM.yyyy"));


            map.put(new Customer(customerName, phoneNumber),
                    new Order(checkNumber, name, product, amount, coast, locate, coordinate, locateDiscr, data));
        }

        for (Map.Entry set : map.entrySet()){
            System.out.println(set);
        }


    }



}
