package com.javarush.task.task18.task1812;

import java.io.*;
//import java.nio.charset.StandardCharsets;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {

    private AmigoOutputStream amigoOutputStream;

    public QuestionFileOutputStream(AmigoOutputStream amigoOutputStream) {
        this.amigoOutputStream = amigoOutputStream;
    }

    public void flush() throws IOException {
        amigoOutputStream.flush();
    }

    public void write(int b) throws IOException {
        amigoOutputStream.write(b);
    }

    public void write(byte[] b) throws IOException {
        amigoOutputStream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        amigoOutputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = reader.readLine();
        reader.close();
        if (answer.contains("Д")) {
            amigoOutputStream.close();
        }

    }

}

