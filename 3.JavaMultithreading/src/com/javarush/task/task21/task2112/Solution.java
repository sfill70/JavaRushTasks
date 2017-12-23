package com.javarush.task.task21.task2112;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        try (FakeConnection fakeConnection = dbConnectionManager.getFakeConnection()) {

            System.out.println("Entering the body of try block.");
            fakeConnection.usefulOperation();
            fakeConnection.unsupportedOperation();

        } catch (Exception e) {
        }
    }
}
