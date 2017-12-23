package com.javarush.task.task29.task2909.human;

/**
 * Created by Sfill on 12.06.2017.
 */
public class Soldier extends Human {


    public Soldier(String name, int age) {
        super(name, age);
    }

    public void live() {
        if (true)
            fight();
    }

    public void fight() {
    }

}
