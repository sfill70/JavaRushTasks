package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sfill on 31.07.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = bis.readLine();
        return text;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> order = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Напечатайте выбранное блюдо");
        while (true) {
            String dish = readString();
            if (dish.equals("exit")) {
                break;
            } else {                                                   //(Arrays.asList(Dish.values()).contains(Dish.valueOf(dish)))
                  try {
                    order.add(Dish.valueOf(dish));
                } catch (IllegalArgumentException e) {
                    writeMessage("Такого блюда нет. Выбрате из списка"); //e.printStackTrace();
                }
            }
        }
        return order;
    }

}
