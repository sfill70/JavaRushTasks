package com.javarush.task.task23.task2311;

/* 
Повторяем threads
*/
public class Solution {
    public final String name;
    public final String food;
    public final String sound;

    public Solution(String name, String food, String sound) {
        this.name = name;
        this.food = food;
        this.sound = sound;
    }
    public void sleep(long milliseconds) {
        System.out.println(name + ": Zzzzzzz..." + (milliseconds / 1000) + " sec");
    }

    public void eat() {
        System.out.println(name + ": Mmmmm, " + food);
    }

    public void play() {
        System.out.println(name + ": " + sound + " " + sound);
    }

    private void someActions() throws InterruptedException {
      //  sleep(1000);
         eat();
        play();
        sleep(1000);
        //   join();
    }

    public void live() throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                try {
                    someActions();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        };
        thread.start();
      thread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new Solution("Amigo", "beef", "knock").live();
       // System.out.println("name" + ": Zzzzzzz..." + (1000 / 1000) + " sec");
    }
}
