package com.javarush.task.task18.task1814;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream  {
  //  public static String name;

 //  public  FileInputStream fileInputStream =new FileInputStream(name);

     public TxtInputStream(String fileName) throws FileNotFoundException, IOException, UnsupportedFileNameException {

        super(fileName);
        if (!fileName.endsWith(".txt")){
            super.close();
            throw new UnsupportedFileNameException();

        }

    }

    public  void close () throws IOException {}

    public static void main(String[] args) throws FileNotFoundException,IOException,  UnsupportedFileNameException{
      //  System.out.println(new TxtInputStream("c:/1.txt"));
    }
}

