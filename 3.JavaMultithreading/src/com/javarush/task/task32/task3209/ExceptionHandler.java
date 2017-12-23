package com.javarush.task.task32.task3209;

/**
 * Created by Sfill on 12.07.2017.
 */
public class ExceptionHandler extends Exception {
    public static void log(Exception e) {
    System.out.println(e.toString());
}
}
