package com.javarush.task.task27.task2709;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    //ReentrantLock locker = new ReentrantLock(); // создаем блокировку
   // Condition condition = locker.newCondition(); // получаем условие, связанное с блокировкой

    public synchronized int get() {
       while (!isValuePresent){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       isValuePresent=false;
       notifyAll();
       System.out.println("Got: " + value);
       return value;
    }

    public synchronized void put(int value) {
        while (isValuePresent){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.value = value;
        isValuePresent=true;
        notifyAll();

        System.out.println("Put: " + value);

    }
}
