package com.javarush.task.task25.task2506;

/**
 * Created by Sfill on 05.06.2017.
 */
public class LoggingStateThread extends Thread {
    public Thread target;
    public LoggingStateThread (Thread target){
       // super(target);
        this.target=target;
        //   setDaemon(true);
    }



    @Override
    public void run()
    {
        State state = target.getState();
        System.out.println(state);
        while (state != State.TERMINATED)
        {
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                new InterruptedException();
            }*/
            if (state != target.getState())
            {
                state = target.getState();
                System.out.println(state);
            }
        }
    }
}