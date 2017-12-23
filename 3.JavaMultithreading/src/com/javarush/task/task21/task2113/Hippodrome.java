package com.javarush.task.task21.task2113;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sfill on 08.05.2017.
 */
public class Hippodrome {


    private List<Horse> horses = new ArrayList<> ();

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }
    public static Hippodrome game;
    public List getHorses() {
        return horses;
    }
    public void move (){
        for (Horse hr:horses) {
            hr.move();
        }
    }
    public void print (){
        for (Horse hr:horses) {
            hr.print();
        }
        for (int i = 0; i <10; i++) {
            System.out.println();
        }
    }
    public void run () throws InterruptedException{
        for (int i = 0; i <100 ; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public Horse getWinner() {
        int win = 0;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).distance > horses.get(win).distance) win = i;
        }
        return horses.get(win);
    }
    public void printWinner(){
        System.out.println("Winner is "+getWinner().getName()+"!");
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<Horse>());
        Horse a = new Horse("Конь", 3, 0);
        Horse b = new Horse("Мерин", 3, 0);
        Horse c = new Horse("Мустанг", 3, 0);
        game.getHorses().add(a);
        game.getHorses().add(b);
        game.getHorses().add(c);
        game.run();
       /* ArrayList <Horse>array = new ArrayList<Horse>(game.getHorses());
           for (Horse  hr: array) {
            System.out.println(hr.getName()+" : "+hr.getDistance());
        }*/
        game.printWinner();


    }


}
