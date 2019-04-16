package com.supermarket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Supermarket {


    static HashMap<Paycheck, Order> map = new HashMap<>();

    public static void initialization(String fileLog){

        for (int i = 0; i<ParserLogFilePaychecks.getPaychecks(fileLog).size(); i++){

            ArrayList<String[]> listChecks = ParserLogFilePaychecks.getPaychecks(fileLog).get(i);

            String paycheckNumber = listChecks.get(0)[0];
            String customerName = listChecks.get(1)[0];
            String phoneNumber = listChecks.get(1)[1];
            String name = listChecks.get(2)[0];
            String product = listChecks.get(3)[0];
            int amount = Integer.parseInt(listChecks.get(3)[1].replaceAll("\\D+", ""));
            int coast = Integer.parseInt(listChecks.get(3)[2].replaceAll("\\D+", ""));
            String locate = listChecks.get(4)[0];
            String coordinate = listChecks.get(4)[1];
            String locateDesc = listChecks.get(4)[2];
            LocalDate date = LocalDate.parse(listChecks.get(5)[0], DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            map.put(new Paycheck(paycheckNumber),
                    new Order(name,
                            new Product(product, amount, coast),
                            new Location(locate, coordinate, locateDesc),
                            new Customer(customerName, phoneNumber),
                            date));

        }
    }
}
