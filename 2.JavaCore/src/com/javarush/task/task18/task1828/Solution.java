package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String fileName = rd.readLine();
        rd.close();
        if ("-u".equals(args[0])){
            update(fileName,args[1],args[2],args[3],args[4]);
        } else if ("-d".equals(args[0])){
            delete(fileName,args[1]);
        }
    }
    public static void update(String fileName, String id, String productName, String price, String quantity){
        try {
            BufferedReader rd = new BufferedReader(new FileReader(fileName));
            ArrayList<String> list = new ArrayList<>();
            while (rd.ready()){
                list.add(rd.readLine());
            }
            rd.close();
            if (id.contains(" "))
                id = id.substring(0,id.indexOf(" "));
            for (int i = 0; i < list.size(); i++) {
                String outId = list.get(i).substring(0,8);
                if (outId.contains(" "))
                    outId = outId.substring(0,outId.indexOf(" "));
                if (outId.equals(id)){
                    if (productName.length()>30)
                        productName = productName.substring(0,30);
                    if (price.length()>8)
                        price = price.substring(0,8);
                    if (quantity.length()>4)
                        quantity = quantity.substring(0,4);
                    while (outId.length()<8){
                        outId+=" ";
                    }
                    while (productName.length()<30){
                        productName+=" ";
                    }
                    while (price.length()<8){
                        price+=" ";
                    }
                    while (quantity.length()<4){
                        quantity+=" ";
                    }
                    list.set(i,outId+productName+price+quantity);
                    break;
                }
            }
            FileOutputStream stream = new FileOutputStream(fileName);
            for (int i = 0; i < list.size(); i++) {
                if (i<list.size()-1){
                    stream.write((list.get(i)).getBytes());
                    stream.write('\n');
                }
                else {
                    stream.write(list.get(i).getBytes());
                }
            }
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e){
        } catch (IOException e){}
    }
    public static void delete(String fileName, String id){
        try {
            BufferedReader rd = new BufferedReader(new FileReader(fileName));
            ArrayList<String> list = new ArrayList<>();
            while (rd.ready()){
                list.add(rd.readLine());
            }
            rd.close();
            if (id.contains(" "))
                id = id.substring(0,id.indexOf(" "));
            for (int i = 0; i < list.size(); i++) {
                String outId = list.get(i).substring(0,8);
                if (outId.contains(" "))
                    outId = outId.substring(0,outId.indexOf(" "));
                if (outId.equals(id)){
                    list.remove(i);
                    break;
                }
            }
            FileOutputStream stream = new FileOutputStream(fileName);
            for (int i = 0; i < list.size(); i++) {
                if (i<list.size()-1){
                    stream.write((list.get(i)).getBytes());
                    stream.write('\n');
                }
                else {
                    stream.write(list.get(i).getBytes());
                }
            }
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e){
        } catch (IOException e){}
    }
}
