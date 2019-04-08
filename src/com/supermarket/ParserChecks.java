package com.supermarket;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParserChecks {

    public static void printChecks(String fileLog) {
        for (ArrayList<String[]> a : getChecks("log.txt")) {
            for (String[] m : a) {
                for (String s : m) {
                    System.out.print(s + "; ");
                }
                System.out.println();
            }
            System.out.println("-----");
        }
    }

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


    private static int getCheckCount(LinkedList<String> listDataLogFile) {
        int checkCount = 0;
        for (String s : listDataLogFile) {
            if (s.equals("-----")) {
                checkCount++;
            }
        }
        return checkCount;
    }


    private static ArrayList<ArrayList<String[]>> separatorListDataLogFile(LinkedList<String> listDataLogFile, int checkCount) {

        ArrayList<ArrayList<String[]>> listChecks = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < checkCount; i++) {

            listChecks.add(new ArrayList<>());

            for (int k = index; k < listDataLogFile.size(); k++) {
                if (listDataLogFile.get(k).equals("-----")) break;
                String[] s = listDataLogFile.get(k).split("; ");
                listChecks.get(i).add(s);
            }
            index = index + listDataLogFile.size() / checkCount;
        }
        return listChecks;
    }

    public static ArrayList<ArrayList<String[]>> getChecks(String fileLog){
        return separatorListDataLogFile(getListDataLogFile(fileLog), getCheckCount(getListDataLogFile(fileLog)));
    }
}
