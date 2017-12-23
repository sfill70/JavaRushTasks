package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        boolean work = true;
        String fileName = new String();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //FileInputStream inputStream = null;


        while (work) {
            try {

               fileName = reader.readLine();
                /*boolean file = new File(fileName).exists();
                if (!file) throw new FileNotFoundException(fileName);*/
               FileInputStream inputStream = new FileInputStream(fileName);
                inputStream.close();
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                reader.close();
                return;
                // work=false
            } finally {
                /*try {
                    inputStream.close();
                } catch (NullPointerException e) {

                }*/

            }

        }

    }
}

