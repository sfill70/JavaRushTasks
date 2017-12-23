package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject (ObjectInputStream objectStream)  {
        A loadedObject;
        try {

             loadedObject = (A) objectStream.readObject();
             return loadedObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       /* catch (Exception e) {
            e.printStackTrace();
            return null;

        }*/
      //  return loadedObject;
    }

    public class A implements Serializable {
        public A () {}
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
