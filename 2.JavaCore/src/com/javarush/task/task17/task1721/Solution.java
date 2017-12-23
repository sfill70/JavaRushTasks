package com.javarush.task.task17.task1721;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();

        allLines = Files.readAllLines(Paths.get(file1), StandardCharsets.UTF_8);
        forRemoveLines = Files.readAllLines(Paths.get(file2), StandardCharsets.UTF_8);

        reader.close();
        System.in.close();

        new Solution().joinData();


/* BufferedReader readerFile = new BufferedReader(new InputStreamReader(new FileInputStream(file1) , StandardCharsets.UTF_8 ));
        InputStream readerFile = new FileInputStream(file1);
        String line;*/

           /* for (String lines : allLines){
            System.out.println(lines);}

            for (String lines1 : forRemoveLines){
                System.out.println(lines1); }*/





    }
    public  void joinData () throws CorruptedDataException {

        if (allLines.containsAll(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

        /*if (allLines.size()<forRemoveLines.size()){
            allLines.clear();
            throw new CorruptedDataException();
        }
        if (allLines.size()>=forRemoveLines.size()){
            int count=0;
            int index;
            for (int i = 0; i <forRemoveLines.size() ; i++) {
                if (allLines.contains(forRemoveLines.get(i))){
                    index=allLines.indexOf(forRemoveLines.get(i));
                    allLines.remove(index);
                    count++;
                }
            }
            if (count==forRemoveLines.size()){
                allLines.clear();
                throw new CorruptedDataException();
            }
        }*/
    }
}
