package com.supermarket;

import com.sun.javafx.collections.MappingChange;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Printer {



    public static void main(String[] args) {

        Printer printer = new Printer();
        Supermarket.initialization("log.txt");
        printer.printMap();

    }


    public ArrayList<String> getColumns(){

        Map<Integer,String> tableColumns = new HashMap<>();

        tableColumns.put(1, "paycheckNumber");
        tableColumns.put(2, "customerName");
        tableColumns.put(3, "phoneNumber");
        tableColumns.put(4, "name");
        tableColumns.put(5, "product");
        tableColumns.put(6, "amount");
        tableColumns.put(7, "coast");
        tableColumns.put(8, "locate");
        tableColumns.put(9, "coordinate");
        tableColumns.put(10, "locateDesc");
        tableColumns.put(11, "date");
        tableColumns.put(12, "all");

        System.out.println("Создание таблицы");


        for (Map.Entry<Integer, String> map : tableColumns.entrySet()){
            System.out.println(map);
        }

        System.out.println("Выберите номера столбцов для создания аналитической таблицы: ");
        Scanner in = new Scanner(System.in);
        String scan = in.nextLine();

        ArrayList<String> columnsNumber = new ArrayList<>(Arrays.asList(scan.split(", ")));
        ArrayList<String> columns = new ArrayList<>();
        for (String str : columnsNumber){
            int s=Integer.parseInt(str);
            columns.add(tableColumns.get(s));
        }

        return columns;

    }

    public String getColumnCompare(ArrayList<String> columns){

        Map<Integer,String> tableColumnsForCompare = new HashMap<>();

        for (int x=0; x<columns.size(); x++){
            tableColumnsForCompare.put(x, columns.get(x));
        }

        for (Map.Entry<Integer, String> mapColumnsForCompare : tableColumnsForCompare.entrySet()){
            System.out.println(mapColumnsForCompare);
        }

        System.out.println("Выберите номер столбца для сортировки ");
        Scanner in = new Scanner(System.in);
        String scan = in.nextLine();
        System.out.println(scan);
        int s=Integer.parseInt(scan);

        return tableColumnsForCompare.get(s);
    }

    public Comparator<Order> getObjectCompare(String columnCompare){

        if ( columnCompare.equals("customerName")){
            class CustomerCompare implements Comparator<Order>{

                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getCustomer().getCustomerName().compareTo(o2.getCustomer().getCustomerName());
                }
            }
            return new CustomerCompare();
        }

        if (columnCompare.equals("coast")){
            class CoastCompare implements Comparator<Order>{

                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getProduct().getCoast() - o2.getProduct().getCoast();
                }
            }
            return new CoastCompare();
        }

        return null;
    }



    public ArrayList<Order> getSortedList(Comparator<Order> objectCompare){

        ArrayList<Order> orders = new ArrayList<Order>(Supermarket.map.values());
        orders.sort(objectCompare);

        return orders;
    }


    public static Map<String, ArrayList<String>> getMapSortedList(ArrayList<Order> orders, ArrayList<String> columns){

        Map<String, ArrayList<String>> mapSortedList = new LinkedHashMap<>();

        for (String s : columns){

            if (s.equals("paycheckNumber")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    Set<Map.Entry<Paycheck,Order>> entrySet=Supermarket.map.entrySet(); //поиск ключа по значению
                    for (Map.Entry<Paycheck,Order> pair : entrySet) {
                        if (value.equals(pair.getValue())) {
                            mapSortedList.get(s).add(pair.getKey().getPaycheckNumber());
                        }
                    }
                }
            }
            if (s.equals("customerName")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getCustomer().getCustomerName());
                }
            }
            if (s.equals("phoneNumber")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getCustomer().getPhoneNumber());
                }
            }
            if (s.equals("name")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getName());
                }
            }
            if (s.equals("product")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getProduct().getProduct());
                }
            }
            if (s.equals("amount")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(Integer.toString(value.getProduct().getAmount()));
                }
            }
            if (s.equals("coast")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(Integer.toString(value.getProduct().getCoast()));
                }
            }
            if (s.equals("locate")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getLocation().getLocate());
                }
            }
            if (s.equals("coordinate")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getLocation().getCoordinate());
                }
            }
            if (s.equals("locateDesc")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getLocation().getLocateDesc());
                }
            }
            if (s.equals("date")){
                mapSortedList.put(s, new ArrayList<>());
                for (Order value : orders){
                    mapSortedList.get(s).add(value.getData().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
            }
        }
        return mapSortedList;
    }

    public void printMap(){
        Printer printer = new Printer();
        Map<String, ArrayList<String>> map = getMapSortedList(printer.getSortedList(getObjectCompare(getColumnCompare(getColumns()))), printer.getColumns());

        for (Map.Entry<String, ArrayList<String>> mapSortedList : map.entrySet()){
            System.out.print(" | "+mapSortedList.getKey()+" | ");
        }

        for (int i=0; i<getSortedList(getObjectCompare(getColumnCompare(getColumns()))).size(); i++) {
            System.out.println();
            for (Map.Entry<String, ArrayList<String>> mapSortedList : map.entrySet()) {

                System.out.print(" | " + mapSortedList.getValue().get(i) + " | ");

            }
        }
    }
}
