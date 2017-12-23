package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1= reader.readLine();
        String file2= reader.readLine();
        String file3= reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(file2  /*"c:/1.txt"*/);
        FileOutputStream outputStream = new FileOutputStream(file1 /*"c:/result.txt"*/,true);

        byte array[]=new byte[inputStream.available()];
        while (inputStream.available()>0){
        inputStream.read(array);
        outputStream.write (array);}
        inputStream.close();

      inputStream = new FileInputStream(file3  /*"c:/2.txt"*/);
        byte array2[]=new byte[inputStream.available()];
        while (inputStream.available()>0){
            inputStream.read(array2);
            outputStream.write (array2);}
            inputStream.close();
            outputStream.close();



        /* PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(*//*file2*//*"c:/1.txt"));
        PrintStream out = new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream("c:/4.txt",true)));
        System.setIn(in);  //Изменяет стандартный поток ввода с "консоли", на из файла
        System.setOut(out); //Изменяет стандартный поток вывода с "консоли" в файл
        System.setErr(out); //Изменяет стандартный поток ошибок с "консоли" в файл
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String s;
        while((s = br.readLine())!=null)
            System.out.println(s);
        out.close(); // Remember this!
        System.setOut(console);*/
    }
}
