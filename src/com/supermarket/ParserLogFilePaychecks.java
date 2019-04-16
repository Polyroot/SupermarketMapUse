package com.supermarket;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParserLogFilePaychecks {

    private static LinkedList<String> getListDataLogFile(String fileLog) {

        LinkedList<String> list = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileLog)));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private static int getPaycheckCount(LinkedList<String> listDataLogFile) {
        int paycheckCount = 0;
        for (String s : listDataLogFile) {
            if (s.equals("-----")) {
                paycheckCount++;
            }
        }
        return paycheckCount;
    }

    private static ArrayList<ArrayList<String[]>> separatorListDataLogFile(LinkedList<String> listDataLogFile, int paycheckCount) {

        ArrayList<ArrayList<String[]>> listPaychecks = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < paycheckCount; i++) {

            listPaychecks.add(new ArrayList<>());

            for (int k = index; k < listDataLogFile.size(); k++) {
                if (listDataLogFile.get(k).equals("-----")) break;
                String[] s = listDataLogFile.get(k).split("; ");
                listPaychecks.get(i).add(s);
            }
            index = index + listDataLogFile.size() / paycheckCount;
        }
        return listPaychecks;
    }

    static ArrayList<ArrayList<String[]>> getPaychecks(String fileLog){
        return separatorListDataLogFile(getListDataLogFile(fileLog), getPaycheckCount(getListDataLogFile(fileLog)));
    }

    public static void printPaychecks(String fileLog) {
        for (ArrayList<String[]> a : getPaychecks("log.txt")) {
            for (String[] m : a) {
                for (String s : m) {
                    System.out.print(s + "; ");
                }
                System.out.println();
            }
            System.out.println("-----");
        }
    }
}