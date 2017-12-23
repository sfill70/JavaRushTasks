package com.javarush.task.task35.task3513;

/**
 * Created by Sfill on 31.08.2017.
 */
public class MoveEfficiency implements Comparable <MoveEfficiency> {
   private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    @Override
    public int compareTo(MoveEfficiency obj) {

        /*int result= Integer.compare(this.numberOfEmptyTiles,  obj.numberOfEmptyTiles );
        if(result != 0) {
            return result;
        }else {
            result = Integer.compare( this.score, obj.score);
            if (result != 0) {
                return result;
            }
            else {return result != 0?result:result;
            }
        }*/


        int result= 0;
        if(obj.numberOfEmptyTiles!=this.numberOfEmptyTiles){
            return Integer.compare( this.numberOfEmptyTiles,  obj.numberOfEmptyTiles);
        }
        else if (obj.score!= this.score){
            return Integer.compare( this.score, obj.score );
        }
        else {return result ;}

    }
}
