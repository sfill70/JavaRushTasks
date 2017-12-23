package com.javarush.task.task25.task2512;


import java.util.ArrayList;
import java.util.Stack;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {

        t.interrupt();

        Stack<Throwable> exceptions = new Stack<>();
        exceptions.push(e);
        Throwable throwable = e.getCause();
        while (throwable != null) {

            exceptions.push(throwable);
            throwable = throwable.getCause();

        }
        while (!exceptions.empty())
        {
            Throwable currentException = exceptions.pop();
            System.out.println(currentException.getClass().getName() + ": " +currentException.getMessage());
        }

       /* ArrayList<Throwable> list= new ArrayList<>();
         list.add(e);
        Throwable throwable = e.getCause();
        while (throwable != null) {

            list.add(throwable);
            throwable = throwable.getCause();

        }
        for (int i = list.size(); i >0 ; i--) {
            System.out.println(list.get(i).getClass().getName()+ ": "+list.get(i).getMessage());
        }*/
    }
    public static void main(String[] args) {
    }
}
