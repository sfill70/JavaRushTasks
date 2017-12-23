package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws Exception{
        /*
        SimpleDateFormat format1 = new SimpleDateFormat("dd MM yyyy");
        Date p = new Date();
        format1.format(p);
        p.setDate(24);
        p.setYear(90);
        p.setMonth(12);
                System.out.println("Current date: " + p);

        String sd = "23 12 1990";
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        Date date = format.parse(sd);
        System.out.println(sd);*/

    }

    public static class PersonScannerAdapter implements PersonScanner {
    private final Scanner fileScanner;
    public PersonScannerAdapter (Scanner fileScanner){this.fileScanner=fileScanner;}

        public Person read() throws IOException {
        String personS = fileScanner.nextLine();
            String array[] = personS.split(" ");
            for (int i = 0; i <array.length ; i++) {
                array[i]=array[i].replaceAll(" ","");
            }
             String firstName = array[1];
             String middleName = array[2];
             String lastName = array[0];
            SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

            Date birthDate= null;
            try {
                birthDate = format.parse(array[3]+" "+ array[4]+" "+ array[5] );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return  new Person  (firstName,middleName,lastName,birthDate);
    }

        public void close() throws IOException {fileScanner.close();};

    }
}
