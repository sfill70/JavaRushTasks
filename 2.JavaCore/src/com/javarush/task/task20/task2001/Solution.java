package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("c:/temp.txt");
            InputStream inputStream = new FileInputStream("c:/temp.txt");

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            System.out.println(ivanov.assets.size()+" "+ivanov.name);
            for (Asset as:ivanov.assets
                 ) {
                System.out.println(as.getName()+as.getPrice());

            }

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson)+" "+ivanov.hashCode()+" "+somePerson.hashCode());
            System.out.println(somePerson+" -"+somePerson.getClass()+" -"+somePerson.name);
            System.out.println(somePerson.assets.size());
            for (Asset as:somePerson.assets
                    ) {
                System.out.println(somePerson.name+as.getName()+as.getPrice());

            }
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {        }
        public Human(String name) {  this.name=name;      }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            if (name!=null){writer.println(this.name);}
             writer.println(assets.size());
            for (Asset as : assets)
            {
                writer.println(as.getName());
                writer.println(as.getPrice());
            }
            writer.flush();


            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            name=reader.readLine();
            int asSize = Integer.parseInt(reader.readLine());
            for (int i = 0; i < asSize; i++)
            {
                String asName = reader.readLine();
                String asPrice = reader.readLine();
                assets.add(new Asset(asName));
                assets.get(assets.size()-1).setPrice(Double.parseDouble(asPrice));
            }
            reader.close();
            //implement this method - реализуйте этот метод
        }
    }
}
