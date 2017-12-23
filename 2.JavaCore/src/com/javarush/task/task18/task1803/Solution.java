package com.javarush.task.task18.task1803;

/*
Самые частые байты
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Solution {
    private static class Repository implements Comparable<Repository> {

        private int num;
        private int count;

        public Repository() {
        }

        public Repository(int num, int count) {
            this.num = num;
            this.count = count;
        }

        public int getNum() {
            return num;
        }

        public int getCount() {
            return count;
        }

        public int compareTo(Repository obj) {

            int result = Integer.compare(obj.getCount(), this.count);
            if (result != 0) {
                return result;
            } else {
                result = Integer.compare(obj.getNum(), this.num);
                if (result != 0) {
                    return result;
                } else {
                    return result != 0 ? result : result;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> nomer = new ArrayList<Integer>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file1);
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            nomer.add(data);
        }
       //"c:/data.txt"
        /* for (int nom : nomer) {
            System.out.print(nom + "/");
            System.out.println(); }*/

        TreeSet <Repository> counts = new TreeSet();
        for (int i = 0; i <nomer.size() ; i++) {
            counts.add(new Repository (nomer.get(i), Collections.frequency(nomer,nomer.get(i))));

        }
        Iterator var7 = counts.iterator();
        Repository y=new Repository();
        Repository x;
      //  int z=0;
        while(var7.hasNext()) {
            x = (Repository)var7.next();
            if (x.getCount()<y.getCount()){break;}
            System.out.print( x.getNum()  +" " /*+ x.getCount()*/ );
           // z += x.getCount();
             y=x;
        }
       /* System.out.println(counts.last().getCount());
        System.out.println(counts.first().getCount());*/
        reader.close();
        inputStream.close();
        System.in.close();
    //    System.out.println(z);
       /* System.out.println();
        System.out.println(counts.size());*/
    }
}