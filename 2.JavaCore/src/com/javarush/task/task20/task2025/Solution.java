package com.javarush.task.task20.task2025;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    private static long cntSearch;
    private static int N;
    private static int[] digitsMultiSet;
    private static List<Long> results;
    private static long maxPow;
    private static long minPow;
    private static long[][] pows;
    private static void genPows(int N) {
        if (N > 20) throw new IllegalArgumentException();
        pows = new long[10][N + 1];
        for (int i = 0; i < pows.length; i++) {
            long p = 1;
            for (int j = 0; j < pows[i].length; j++) {
                pows[i][j] = p;
                p *= i;
            }
        }
    }
    private static boolean check(long pow) {
        cntSearch++;
        if (pow >= maxPow) return false;
        if (pow < minPow) return false;
        int[] testMultiSet = new int[10];
        while (pow > 0) {
            int i = (int) (pow % 10);
            testMultiSet[i]++;
            pow = pow / 10;
        }
        for (int i = 0; i < 10; i++) {
            if (testMultiSet[i] != digitsMultiSet[i]) return false;
        }
        return true;
    }
    private static void search(int digit, int unused, long pow) {
        if (digit == 10) {
            if (check(pow)) results.add(pow);
            return;
        }
        if (digit == 9) {
            digitsMultiSet[digit] = unused;
            search(digit + 1, 0, pow + unused * pows[digit][N]);
        } else {
            for (int i = 0; i <= unused; i++) {
                digitsMultiSet[digit] = i;
                search(digit + 1, unused - i, pow + i * pows[digit][N]);
            }
        }
    }
    public static List<Long> generate(int maxN) {
        if (maxN >= 20) throw new IllegalArgumentException();
        genPows(maxN);
        results = new ArrayList<>();
        digitsMultiSet = new int[10];
        cntSearch = 0;
        for (N = 1; N <= maxN; N++) {
            int count=0;
            minPow=1;

            while (count<N-1){
                minPow*=10;
                count++;
            }
            count=0;
            maxPow=1;
            while (count<=N){
                maxPow*=10;
                count++;
            }
            count=0;
            // minPow = (long) Math.pow(10, N - 1);
            //  maxPow = (long) Math.pow(10, N);
            search(0, N, 0);
        }
        Collections.sort(results);
        return results;
    }
    public static long[] getNumbers(long N) {
        List<Long> list = generate(19);
        List<Long> listTotal = new ArrayList<Long>();
        for (Long aLong : list) {
            if (aLong < N) {
                listTotal.add(aLong);
            }
        }
        long[] result = new long[listTotal.size()];
        int i = 0;
        for (long aLong : listTotal) {
            result[i] = listTotal.get(i);
            i++;
        }
        return result;
    }
   /* public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long[] arr = getNumbers(Long.MAX_VALUE); //Long.MAX_VALUE
        // System.out.println(Arrays.toString(arr));
        long finish = System.currentTimeMillis();
        System.out.println(results);
        System.out.println("Time consumed: " + (finish - start)+ " ms");
        System.out.printf("Execution spend %d,%d sec & %d mb of memory.",
                ((finish - start)/1000), ((finish - start) % 1000), (Runtime.getRuntime().totalMemory()
                        - Runtime.getRuntime().freeMemory())/1024/1024);
    }*/
}