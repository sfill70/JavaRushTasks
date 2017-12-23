package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1= reader.readLine();
        String file2= reader.readLine();
        reader.close();

        /*DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("c:/1.txt")));*/

        /*DataInputStream in = new DataInputStream(
                 new ByteArrayInputStream(
                       new byte[ new FileInputStream("c:/1.txt").available()]));*/

        StringBuilder sb = new StringBuilder();
        byte b = 0;
        FileInputStream in = new FileInputStream(file1  /*"c:/1.txt"*/);
        byte array[] = new byte[in.available()];
        while (in.available() > 0) {
            in.read(array);
        }
        in.close();
        for (int i = 0; i < array.length; i++) {
            {
                b = array[i];
                if (b == 45 || b == 46 || b == 48 || b == 49 || b == 50 || b == 51 || b == 52 || b == 53 || b == 54 || b == 55 || b == 56 || b == 57 || b == 32)
            /*if(true)*/ {
                    sb.append((char) b);
                    // System.out.println(b);
                }
            }
        }

        String sbSt = sb.toString();
        sbSt = sbSt.replaceAll("[\\s]{2,}", " ");
        String[] arrayString = sbSt.split(" ");

        for (String by : arrayString) {
            System.out.println(by);

        }
        for (String by : arrayString) {
            System.out.println(Math.round(Double.parseDouble(by)));

        }


        FileOutputStream outputStream = new FileOutputStream(file2,true /*"c:/result.txt"*/);

        for (String by : arrayString) {
         //  byte[] i = (Long.toString(Math.round(Double.parseDouble(by))) +" ").getBytes();
            outputStream.write((String.valueOf(Math.round(Double.parseDouble(by))) +" ").getBytes());
           /* for (byte z : i) {
              //  System.out.println((int) z);
                outputStream.write(z);
            }*/

        }
      outputStream.close();
        //  outputStream.write("\r\n".getBytes());

    }
}
