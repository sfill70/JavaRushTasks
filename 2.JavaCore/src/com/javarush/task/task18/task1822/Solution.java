package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        BufferedReader idReader = new BufferedReader(new InputStreamReader(new FileInputStream(file /*"c:/result.txt"*/), StandardCharsets.UTF_8));
        String line;
        /*HashMap<Integer, String> product = new HashMap();
        ArrayList<String> list = new ArrayList();*/
        while ((line = idReader.readLine()) != null) {
            line.trim();
            System.out.println(line);
            if(!line.isEmpty())
            { //list.add(line);
                if (line.indexOf(" ")>0){
                if (args[0].equals(line.substring(0,line.indexOf(" ")))){
                    System.out.println(line);
                }
                }
            }
        }
        idReader.close();
       /* for (int i = 0; i < list.size(); i++) {

            char[] chars = list.get(i).toCharArray();
            for (int j = 0; j < chars.length && j < 4; j++) {
                if (chars[j] < 48 || chars[j] > 57) {
                    list.remove(list.get(i));
                }
            }
        }

        int id = 0;
        String produ=new String();
        String prod = new String();
        int poz=0;
        for (int i = 0; i < list.size(); i++) {
         //   System.out.println(list.get(i));
          poz= list.get(i).indexOf(" ");
          id=Integer.parseInt(list.get(i).substring(0,poz));
          prod=list.get(i).substring(poz,list.get(i).length());
            product.put(id,prod);
        }
        // product.put(id,sb.toString());}



        for (Map.Entry<Integer, String> prod1 : product.entrySet()) {
           // System.out.println(prod1.getValue());
                   if (Integer.parseInt(args[0])==( prod1.getKey() )) {System.out.println(prod1.getKey()+" "+prod1.getValue());
            }
        }*/


    }
}
