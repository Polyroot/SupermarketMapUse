package com.supermarket;

import com.supermarket.Objects.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrdersAnalyser {

    public static void main(String[] args) {

        OrdersAnalyser printer = new OrdersAnalyser();
        printer.setMap("log.txt");
        printer.setTableColumns();
        printer.printTableColumns();
        printer.setColumns(new Scanner(System.in));
        printer.setTableColumnsForCompare();
        printer.printTableColumnsForCompare();
        printer.setColumnForCompare(new Scanner(System.in));
        printer.setMapSortedOrdersList(printer.getSortedOrdersList(), printer.getColumns());

        printer.printMapSortedOrdersList();

//        printer.getFixMapSortedOrdersList();

    }

    private HashMap<Paycheck, Order> map = new HashMap<>();

    public HashMap<Paycheck, Order> getMap() {
        return map;
    }

    public void setMap(String fileLog){

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

    private Map<Integer,String> tableColumns = new HashMap<>();

    public Map<Integer, String> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns() {
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
    }

    public void printTableColumns(){

        System.out.println("Создание таблицы");

        for (Map.Entry<Integer, String> map : getTableColumns().entrySet()){
            System.out.println(map);
        }

        System.out.println("Выберите номера столбцов для создания аналитической таблицы: ");
    }

    //---------------

    private ArrayList<String> columns = new ArrayList<>();

    public ArrayList<String> getColumns() {
        return columns;
    }

    public void setColumns(Scanner in) {

        String scan = in.nextLine();

        ArrayList<String> columnsNumber = new ArrayList<>(Arrays.asList(scan.split(", ")));

        for (String str : columnsNumber){
            int s=Integer.parseInt(str);
            this.columns.add(getTableColumns().get(s));
        }
    }

    //---------------

    private Map<Integer,String> tableColumnsForCompare = new HashMap<>();


    public Map<Integer, String> getTableColumnsForCompare() {
        return tableColumnsForCompare;
    }

    public void setTableColumnsForCompare() {
        for (int x=0; x<getColumns().size(); x++){
            this.tableColumnsForCompare.put(x, getColumns().get(x));
        }
    }

    public void printTableColumnsForCompare(){

        for (Map.Entry<Integer, String> mapColumnsForCompare : getTableColumnsForCompare().entrySet()){
            System.out.println(mapColumnsForCompare);
        }

        System.out.println("Выберите номер столбца для сортировки ");

    }

    //---------------

    private String columnForCompare;

    public String getColumnForCompare() {
        return columnForCompare;
    }

    public void setColumnForCompare(Scanner in) {

        String scan = in.nextLine();
        int s = Integer.parseInt(scan);
        this.columnForCompare = getTableColumnsForCompare().get(s);
        System.out.println("Вы выбрали "+columnForCompare);
    }

    //---------------

    public Comparator<Order> getObjectCompare(){

        if ( getColumnForCompare().equals("customerName")){
            class CustomerCompare implements Comparator<Order>{

                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getCustomer().getCustomerName().compareTo(o2.getCustomer().getCustomerName());
                }
            }
            return new CustomerCompare();
        }


        if (getColumnForCompare().equals("coast")){
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

    public ArrayList<Order> getSortedOrdersList(){
        ArrayList<Order> sortedOrdersList = new ArrayList<Order>(getMap().values());

        sortedOrdersList.sort(getObjectCompare());
        return sortedOrdersList;
    }

//---------------

    private Map<String, LinkedList<String>> mapSortedOrdersList = new LinkedHashMap<>();

    public Map<String, LinkedList<String>> getMapSortedOrdersList() {
        return mapSortedOrdersList;
    }

    public void setMapSortedOrdersList(ArrayList<Order> sortedOrdersList, ArrayList<String> columns) {
        for (String s : columns){

            if (s.equals("paycheckNumber")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    Set<Map.Entry<Paycheck,Order>> entrySet = getMap().entrySet(); //поиск ключа по значению
                    for (Map.Entry<Paycheck,Order> pair : entrySet) {
                        if (value.equals(pair.getValue())) {
                            mapSortedOrdersList.get(s).add(pair.getKey().getPaycheckNumber());
                        }
                    }
                }
            }

            if (s.equals("customerName")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getCustomer().getCustomerName());
                }
            }
            if (s.equals("phoneNumber")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getCustomer().getPhoneNumber());
                }
            }
            if (s.equals("name")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getName());
                }
            }
            if (s.equals("product")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getProduct().getProduct());
                }
            }
            if (s.equals("amount")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(Integer.toString(value.getProduct().getAmount()));
                }
            }
            if (s.equals("coast")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(Integer.toString(value.getProduct().getCoast()));
                }
            }
            if (s.equals("locate")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getLocation().getLocate());
                }
            }
            if (s.equals("coordinate")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getLocation().getCoordinate());
                }
            }
            if (s.equals("locateDesc")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getLocation().getLocateDesc());
                }
            }
            if (s.equals("date")){
                mapSortedOrdersList.put(s, new LinkedList<>());
                for (Order value : sortedOrdersList){
                    mapSortedOrdersList.get(s).add(value.getData().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
            }
        }
    }

    public Map<String, LinkedList<String>> getFixMapSortedOrdersList(){

        Map<String, LinkedList<String>> fixMapSortedOrdersList = new LinkedHashMap<>();

        for (Map.Entry<String, LinkedList<String>> mapSortedList : getMapSortedOrdersList().entrySet()){
            LinkedList<String> list = new LinkedList<>();
            int sizeColumn=1;
            String key=mapSortedList.getKey();
            if (sizeColumn<=key.length()){
                sizeColumn=key.length();
            }

            for (String s : mapSortedList.getValue()){
                if (sizeColumn<=s.length()){
                    sizeColumn = s.length();
                }
            }

            while (sizeColumn!=key.length()){
                key=key+" ";
            }

            for (String s : mapSortedList.getValue()){
                while (sizeColumn!=s.length()){
                    s=s+" ";
                }
                list.add(s);
            }
            fixMapSortedOrdersList.put(key, list);
        }
        return fixMapSortedOrdersList;
    }



    public void printMapSortedOrdersList(){

        Map<String, LinkedList<String>> map = getFixMapSortedOrdersList();

        for (Map.Entry<String, LinkedList<String>> mapSortedList : map.entrySet()){
            System.out.print(" | "+mapSortedList.getKey()+" | ");
        }

        for (int i=0; i<getSortedOrdersList().size(); i++) {
            System.out.println();
            for (Map.Entry<String, LinkedList<String>> mapSortedList : map.entrySet()) {

                System.out.print(" | " + mapSortedList.getValue().get(i) + " | ");

            }
        }
    }




}
