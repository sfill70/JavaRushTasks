package com.javarush.task.task18.task1813;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
AmigoOutputStream
*/

public class AmigoOutputStream extends FileOutputStream{
    public FileOutputStream fileOutputStream;
    public static String fileName = "C:/result.txt";



    public AmigoOutputStream(FileOutputStream fileStream) throws FileNotFoundException {
        super(fileName);

        this.fileOutputStream = fileStream ;
    }

    @Override
    public void flush () throws IOException {
        fileOutputStream.flush();
    }
    @Override
    public void write (int b) throws IOException {
        fileOutputStream.write(b);
    }
    @Override
    public void write (byte[]b) throws IOException {
        fileOutputStream.write(b);
    }
    @Override
    public void write (byte b[], int off, int len ) throws IOException {
        fileOutputStream.write(b,off, len );
    }


    @Override
    public void close() throws IOException {
        fileOutputStream.flush();
        fileOutputStream.write("JavaRush © All rights reserved.".getBytes());
        fileOutputStream.close();}

    public static void main(String[] args) throws FileNotFoundException {
        new AmigoOutputStream(new FileOutputStream(fileName));
        System.out.println(fileName);
    }

}
