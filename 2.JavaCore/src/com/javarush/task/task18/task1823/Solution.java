package com.javarush.task.task18.task1823;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        while (!"exit".contains(fileName=reader.readLine())){
               new ReadThread (fileName).start();
                //System.out.println((fileName));
        }
        /*for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println( entry.getKey() + " " + entry.getValue());
        }*/
    }
//   c:/result.txt            c:/4.txt      c:/3.txt
    public static class ReadThread extends Thread    {
        String fileName;
        public ReadThread(String fileName) { /*super();*/
        this.fileName=fileName;
        //implement constructor body
        }
        public synchronized void run()  {

            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                long[] arrBytes = new long[256];
                long startTime = System.currentTimeMillis();

                byte fileImage[]=new byte[inputStream.available()];
                long fileSize=fileImage.length;
                inputStream.read(fileImage);
                for (int i = 0; i <fileSize ; i++) arrBytes[fileImage[i] & 0b11111111]++;
                inputStream.close();
                long rez=0;
                int iN=0;
                for (int i = 0; i <arrBytes.length ; i++) {
                    if (i==0&&arrBytes[i]>0){rez=arrBytes[i];iN=i;}
                    if(arrBytes[i]>rez ) {rez =arrBytes[i];iN=i;}
                 }
                 resultMap.put(fileName,iN);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // implement file reading here - реализуйте чтение из файла тут
    }
}
