package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sfill on 20.06.2017.
 */
public class ConsoleHelper {
    private static BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString(){
        String line=null;
        while (true) {
            try {
                line=reader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз");
             //   continue;
            }
        }
        return line;}

       public static int  readInt(){
        int n=0;
           while (true) {
               try {
                   n=Integer.parseInt(readString());
                   break;
               } catch (NumberFormatException e) {
                   System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                //   continue;
               }
           }
           return n;}
}
