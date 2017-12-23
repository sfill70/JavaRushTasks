package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable{
    int node;
    List<Solution> edges = new LinkedList<>();
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.reset();
        out.close();
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        in.close();
    }

    public static void main(String[] args) {

    }
}
