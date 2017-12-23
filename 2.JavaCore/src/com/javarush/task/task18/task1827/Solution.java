package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

public class Solution {


    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList<>();
        // String id = new String();
        if ("-c".equals(args[0])) {
            // System.out.println(args.length);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();


            //  String fileName ="c:/4.txt";
            //  id = createId(fileName) ;

            BufferedReader idReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            String line;
            int maxId = 0;
            while ((line = idReader.readLine()) != null) {
                list.add(line);
                // System.out.println(line);
                StringBuilder sb = new StringBuilder();     //п.п 2
                if (line.length() > 2) {
                    char[] chArray = line.substring(0, 9).trim().toCharArray();


                    for (int i = 0; i < chArray.length; i++) {
                        char b = chArray[i];
                        if (Character.isDigit(b)) {
                            sb.append((char) b);
                            //System.out.println(b);
                        }
                    }
                }
                int id = Integer.parseInt(sb.toString() /*replaceAll(" ","")*/);
                // int id = Integer.parseInt(line.substring(0, 8).replaceAll(" ","")); МОЖНО ОБОЙТИСЬ ТОЛЬКО ЭТОЙ СТРОЧКО и убрать все начиная с п.п 2, но у меня в файле певая строка начинается с непонятного невидимого символа
                if (id > maxId) {
                    maxId = id;
                }
            }
            maxId = maxId + 1;

            idReader.close();
            String id = String.valueOf(maxId);

            String productName = args[1];
            for (int i = 2; i < args.length - 2; i++) {
                productName += " " + args[i];
            }

            String price = String.format(Locale.US, "%.2f", Double.parseDouble(args[args.length - 2].replaceAll(" ", "")));        //args[args.length-2];
            String quantity = args[args.length - 1];

            String outputString = String.format("%-8.8s%-30.30s%-8.8s%-4.4s", id, productName, price, quantity);
            list.add(outputString);

            FileWriter writer = new FileWriter(fileName, false);

            for (String st:list) {
                writer.write(st);
                writer.write(System.getProperty("line.separator"));
            }

            writer.flush();
            writer.close();

           /* BufferedWriter write = new BufferedWriter  (new OutputStreamWriter(new FileOutputStream(fileName,true)));
        //FileOutputStream outputStream = new FileOutputStream(fileName,true);
        write.write("\r\n");
        write.write(outputString);
        write.flush();
        write.close();*/
           /* FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            if ( reader2.readLine() != null) {
                fileWriter.write(System.getProperty("line.separator"));
            }
            reader2.close();
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(outputString);
            fileWriter.flush();
            fileWriter.close();*/




            //   System.out.println(outputString);

        }
    }

}
