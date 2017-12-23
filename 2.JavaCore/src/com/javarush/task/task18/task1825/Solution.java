package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;

public class Solution {
    public static void main(String[] args)  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        String outFileName;
        ArrayList<String>files= new ArrayList<>();
       int tmp;
       boolean work = true;
       while (!"end".equals(fileName=reader.readLine())){
                work=true;
            try {
                tmp=Integer.parseInt(fileName.substring(fileName.lastIndexOf(".")+5, fileName.length()));
            } catch (NumberFormatException e) {

                System.out.println("Ввел херню");
             //   e.printStackTrace();
                work=false;
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println("Ввел херню");
              //  e.printStackTrace();
                work=false;
            }
            if (work){
            files.add(fileName);}
            //System.out.println((fileName));
        }
        reader.close();

            Collections.sort(files,new Comparator<String>() {
                public int compare(String s, String s2) {
                    return Integer.compare(Integer.parseInt(s.substring(s.lastIndexOf(".")+5, s.length())),Integer.parseInt(s2.substring(s2.lastIndexOf(".")+5, s2.length())));
                }
            });
        outFileName = files.get(0).substring(0,files.get(0).lastIndexOf("."));


        /*File newFile = new File(outFileName);
        newFile.createNewFile();*/

        byte[] buffer = new byte[32768];
        for (int i=0; i<files.size();i++) {
            FileInputStream inputStream = new FileInputStream(files.get(i));
            FileOutputStream outputStream = new FileOutputStream (outFileName,true);

            while (inputStream.available()>0) {
                int readBytesCount = inputStream.read(buffer);
                if (readBytesCount > 0) {
                    // данные были считаны - есть, что записать
                    outputStream.write(buffer, 0, readBytesCount);
                }
          }
            outputStream.close();
            inputStream.close();

        }

        for (int i=0; i<files.size();i++) {
            System.out.println(files.get(i));
        }
    }

}
