package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> listFileTree = new ArrayList<>();
        Queue<File> queueFiles = new PriorityQueue<File>();
        //Добавляет аргумент метода в вехушку очереди
        queueFiles.add(new File(root));
        // Пока очередь не пустая
        while (!queueFiles.isEmpty()) {
            //Берм файл из верхушки очереди с удаением
            File file = queueFiles.poll();
           //Если файл дериктория записываем файлы в очередь
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    queueFiles.add(f);
                }
            }
            // Если файл - фаайл добавляем его в List
            else {listFileTree.add(file.getAbsolutePath());}
        }
        return listFileTree;
    }

    public static void main(String[] args)  {
        String st = new String("c:/Qwert");
        try {
            for ( String s:  getFileTree(st)) {
                System.out.println(s+" ");
            }
        } catch (IOException e) {

        }

    }
}
