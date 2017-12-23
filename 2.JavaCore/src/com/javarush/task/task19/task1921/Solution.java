package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException{
        String fileName = args[0];
        BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
        String tmp= new String();
        int year=0;
        int month=0;
        int day=0;
        while (file1reader.ready()){
            tmp = file1reader.readLine();
            String[]array=tmp.split(" ");
            year=Integer.parseInt(array[array.length-1].replaceAll("[^0-9]",""));
            month =Integer.parseInt(array[array.length-2].replaceAll("[^0-9]",""));
            day=Integer.parseInt(array[array.length-3].replaceAll("[^0-9]",""));

            String name=new String();
            name=array[0];
//          StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <array.length-3 ; i++) {
            name += " "+array[i];
               /* stringBuilder.append(array[i]);
                stringBuilder.append(" ");*/
            }
                PEOPLE.add(new Person(/*stringBuilder.toString().trim()*/ name, new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse (day+"/"+month+"/"+year)));

        }
        file1reader.close();
    }
}
