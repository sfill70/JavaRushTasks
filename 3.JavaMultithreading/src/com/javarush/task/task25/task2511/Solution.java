package com.javarush.task.task25.task2511;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler =new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                String name =t.getName().replaceAll(".","*");
                System.out.println(e.getMessage().replaceAll(t.getName(), name));
                //System.out.println(e.getMessage());
            }
        };    //init handler here
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        new Thread(new Solution(new TimerTask() {
            @Override
            public void run() {
              //  Thread.sleep(0);
                throw new ArithmeticException();
            }
        })).start();
    }
}