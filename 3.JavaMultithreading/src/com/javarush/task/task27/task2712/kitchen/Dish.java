package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by Sfill on 31.07.2017.
 */
public enum  Dish {


    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        if (values().length == 0) {
            return "";
        }
        else {
        StringBuilder menu = new StringBuilder();
        for (Dish dish: Dish.values()) {
            menu.append(dish).append(" ");

        }
        return menu.toString().trim();}
        //return Arrays.toString(Dish.values());
    }
}
