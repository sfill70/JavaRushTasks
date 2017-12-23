package com.javarush.task.task30.task3004;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Sfill on 26.08.2017.
 */
//extends ForkJoinTask<T>
public class BinaryRepresentationTask extends RecursiveTask   <String> {
    private final int numDex;
    //List<String>numBi = new LinkedList<>();

    public BinaryRepresentationTask(int numDex) {
        this.numDex = numDex;
    }


    @Override
    protected String compute() {

        int a = numDex % 2;
        int b = numDex / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
           // System.out.println(task.invoke());
            return task.join() + result;


        }
        return result;
    }
}
