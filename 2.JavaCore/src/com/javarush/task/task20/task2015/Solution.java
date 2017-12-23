package com.javarush.task.task20.task2015;

import java.io.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/* 
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        while (speed>0)
        System.out.println(speed--+" -"+ this.hashCode());
        try {
            if(speed%100==0){sleep(10000);}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        Solution savedObject=new Solution(100000);
        oos.writeObject(savedObject);
        oos.close();

        System.out.println(savedObject);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        Solution loadedObject = (Solution) ois.readObject();
        ois.close();
        System.out.println(loadedObject);
    }
}
