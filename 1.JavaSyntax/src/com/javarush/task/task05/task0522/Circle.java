package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
*/

public class Circle {
    public double x;
    public double y;
    public double radius;

    public Circle() {
        this.x = 0;
        this.y = 0;
        this.radius = 10;
    }

    public Circle(double x) {
        this.x = x;
        this.y = 0;
        this.radius = 10;
    }

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = 10;
    }

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    //напишите тут ваш код

    public static void main(String[] args) {

    }
}