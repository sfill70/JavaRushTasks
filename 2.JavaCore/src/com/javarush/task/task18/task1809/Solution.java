package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(file1);
        FileOutputStream outputStream = new FileOutputStream(file2);
        // c:/data.txt
        // "c:/result.txt"
        // "c:/4.txt"
        long startTime = System.currentTimeMillis();

        /* if (inputStream.available()>0) {
            byte buffer[]  = new byte[inputStream.available()];
            inputStream.read(buffer);
          //  System.out.println(Arrays.toString(buffer));
            for (int i = buffer.length-1; i >=0 ; i--) {
                outputStream.write( buffer[i]);
            }
        }        //ВРЕМЯ РАБОТЫ 6-8 мс*/


        //   Collections.sort(Arrays.asList(buffer), Collections.reverseOrder());
      //  ArrayUtils.reverse(buffer);

        if (inputStream.available() > 0) {
            byte buffer[] = new byte[inputStream.available()];
            inputStream.read(buffer);

            int i = 0;
            int j = buffer.length - 1;
            byte tmp;
            while (j > i) {
                tmp = buffer[j];
                buffer[j] = buffer[i];
                buffer[i] = tmp;
                j--;
                i++;
            }

          //Так разбивает при записи в файлна строки.
           /* for (int u = 0; u < buffer.length; u++) {
                if (u == buffer.length - 1) {
                    outputStream.write(buffer[u]);
                } else {
                    outputStream.write(buffer[u]);
                    outputStream.write("\n".getBytes());
                }*/

            // А так не разбивает при записи на строки.
           outputStream.write(buffer);       //ВРЕМЯ РАБОТЫ 1мс.
            }

            inputStream.close();
            outputStream.close();
            long finishTime = System.currentTimeMillis();
            System.out.println("\nвремя работы=" + (finishTime - startTime) + "ms.");
        }

}
