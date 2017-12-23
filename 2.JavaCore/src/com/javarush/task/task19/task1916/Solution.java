package com.javarush.task.task19.task1916;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException{
      //  System.out.println(5%5);
        ArrayList <String>list= new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();

        BufferedReader file1reader = new BufferedReader(new FileReader(fileName1));
        BufferedReader file2reader = new BufferedReader(new FileReader(fileName2));
        while (file1reader.ready()){
            for (int i = 0; i <5 ; i++) {
                list.add(file1reader.readLine());
            }
            break;
        }
        file1reader.close();
        int i =0;
        String st=new String();
        while (file2reader.ready()){
            st=file2reader.readLine();
            if(st.equals(list.get(i%5)))
            {
                lines.add(new LineItem(Type.SAME, list.get(i%5)));
                i++;
            }
            else {
                if (st.equals(list.get((i+1)%5))){
                    lines.add(new LineItem(Type.REMOVED, list.get(i%5)));
                    lines.add(new LineItem(Type.SAME, list.get((i+1)%5)));
                    i=i+2;
                }
                else   {
                    lines.add(new LineItem(Type.ADDED, st));
                    st=file2reader.readLine();
                    lines.add(new LineItem(Type.SAME, list.get(i%5)));
                    i++;
                }
            }
        }
        file1reader.close();
        file2reader.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
