package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
//c:\13.txt

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader readerF = new BufferedReader(new FileReader(fileName));
      //  BufferedReader readerF = new BufferedReader(new FileReader("c:/13.txt"));
        StringBuilder sb = new StringBuilder();
        while (readerF.ready()) {
            sb.append(readerF.readLine() + " ");
        }
        readerF.close();
       // sb.deleteCharAt(0);

        String[] arr = sb.toString().trim().replaceAll('\uFEFF'+"","").split("\\s+"); //

        for (int i = 0; i <arr.length ; i++) {
            arr[i]=arr[i].replaceAll("-","");
        }
        System.out.println(Arrays.toString(arr));
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(i)) continue;
            for (int j = i+1; j < arr.length; j++) {
                String x = new StringBuilder(arr[j]).reverse().toString();
                if (x.equals(arr[i])) {
                    Pair pair = new Pair();
                    pair.first = x;
                    pair.second = arr[j];
                    result.add(pair);
                    set.add(j);
                    break;
                }
            }
        }

    /*public static void main(String[] args) throws IOException{
        List<String> words = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        while (fileReader.ready())
            words.addAll(Arrays.asList(fileReader.readLine().split(" ")));
        fileReader.close();

        for(int i = 0; i < words.size(); i++)
        {
            for(int j = 0; j < words.size();)
            {
                // System.out.println(j+":"+i);
                if(words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i)
                {
                    Pair pair = new Pair();
                    pair.first = words.get(j);
                    pair.second = words.get(i);
                    result.add(pair);
                    words.remove(j);
                    words.remove(i);
                  //  System.out.println(j+":"+i);
                    j = 0;
                }
                else
                    j++;
            }
        }*/
        for (Pair pair : result) {
            System.out.println(pair);
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
