package com.javarush.task.task20.task2016;

import java.io.*;

/*
Минимум изменений
*/
public class Solution {
    public static class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public static class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        C savedObject=new C("NAME");
        oos.writeObject(savedObject);
        oos.close();

        System.out.println(savedObject);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        C loadedObject = (C) ois.readObject();
        ois.close();
        System.out.println(loadedObject+" -"+loadedObject.getClass());
        System.out.println(loadedObject.equals(savedObject));
    }
}
