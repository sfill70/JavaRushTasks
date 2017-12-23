    package com.javarush.task.task17.task1710;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {

        if (args[0].equals("-c"))
        {
            if (args[2].equals("м"))
            {
                try {

                Date data = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                allPeople.add(Person.createMale(args[1], data ));
            }
                catch (ParseException e) {
                    e.printStackTrace();
                }

            } else if (args[2].equals("ж"))
            {
                try {

                    Date data = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                    allPeople.add(Person.createFemale(args[1], data ));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(allPeople.size()-1);
        } else if (args[0].equals("-u"))
        {
            allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
            if (args[3].equals("м"))
            {
                allPeople.get(Integer.parseInt(args[1])).setSex(Sex.MALE);
            } else if (args[3].equals("ж"))
            {
                allPeople.get(Integer.parseInt(args[1])).setSex(Sex.FEMALE);
            }
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                 Date date = new Date();
                 date = format.parse(args[4]);
                allPeople.get(Integer.parseInt(args[1])).setBirthDay(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (args[0].equals("-d"))
        {
            int i = Integer.parseInt(args[1]);
            allPeople.get(i).setBirthDay(null);
            allPeople.get(i).setSex(null);
            allPeople.get(i).setName(null);
        } else if (args[0].equals("-i"))
        {
            String out = "";
            out += allPeople.get(Integer.parseInt(args[1])).getName() +" ";
            out += ((allPeople.get(Integer.parseInt(args[1])).getSex() == Sex.MALE) ? "м" : "ж") + " ";
            out += new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(Integer.parseInt(args[1])).getBirthDay());
            System.out.println(out);
        }
/*
        SimpleDateFormat format0 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dat = "17/02/2001";
        try {
          //  Date date =new Date();
            Date date = format0.parse(dat);

            System.out.println(format0.format(date) +" "+ date.toString()+ " "+date.getClass());
        } catch (ParseException e) {
            e.printStackTrace();
        }
     SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
       System.out.println(format1.format(allPeople.get(0).getBirthDay()));*/

        //start here - начни тут
    }
}
