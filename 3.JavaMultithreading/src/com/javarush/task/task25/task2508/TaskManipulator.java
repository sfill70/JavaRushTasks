package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable , CustomThreadManipulator {

    public Thread t;
    public void start(String threadName){
        t = new Thread(this, threadName);
        t.start();
    }
    public void stop(){
        // Thread.currentThread().interrupt();
        t.interrupt();
    }

    @Override
    public void run() {
        while (!t.isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(100);

            } catch (InterruptedException e) {
               // e.printStackTrace();
                break;
            }
        }
    }
}
