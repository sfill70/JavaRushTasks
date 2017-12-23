package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//c:/1.txt

public class Solution {
    public static void main(String[] args) throws DownloadException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!reader.readLine().isEmpty()){
            FileInputStream inputStream = new FileInputStream(reader.readLine());



                if (inputStream.available()<1000) {
                    reader.close();
                    inputStream.close();
                    throw new DownloadException();
                }
                else { byte fileImage[]=new byte[inputStream.available()];
                    inputStream.read(fileImage);}


        }

    }

    public static class DownloadException extends Exception {

    }
}
