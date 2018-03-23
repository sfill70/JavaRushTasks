package com.javarush.task.task37.task3712;

/**
 * Created by Sfill on 23.03.2018.
 */
public abstract class Game {
  //  protected Game game;
    public abstract void prepareForTheGame();
    public abstract void playGame();
    public abstract void congratulateWinner();
    public void run(){
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }

}
