package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Sfill on 29.08.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4; // размер поля
    private Tile[][] gameTiles;               // игровое поле
    int score;                                // счет
    int maxTile;                              // значение макимальной плитки
    private boolean isSaveNeeded = true;

    Stack<Tile[][]> previousStates = new Stack();  //Стек состояния игорового поля
    Stack<Integer> previousScores = new Stack();    //Стек для хранения score;


    public Model() {
        this.score = 0;
        this.maxTile = 2;
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    //возвращает список пустых клеток
    private ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    //меняет значени случайной пустой плитки на 2 или 4, с вероятостью 9 к 1ю
    private void addTile() {
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && emptyTiles.size() != 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    // сжатие одного ряда влево
    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value == 0) {
                for (int j = i + 1; j < tiles.length; j++) {
                    if (tiles[j].value > 0) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        isChanged = true;
                        break;
                    }
                }
            }
        }
        return isChanged;
    }

    // сложение клеток !!! ВОЗМОЖНО ПРИДЕТСЯ ВЗЯТЬ ЧУЖОЙ МЕТОД
    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value * 2;
                score += tiles[i].value;
                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                isChanged = true;
            }
        }
        return isChanged;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(this.gameTiles);
        }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
        }
    }

    public void down() {
        saveState(this.gameTiles);
        rotation();
        left();
        rotation();
        rotation();
        rotation();
    }

    public void right() {
        saveState(this.gameTiles);
        rotation();
        rotation();
        left();
        rotation();
        rotation();
    }

    public void up() {
        saveState(this.gameTiles);
        rotation();
        rotation();
        rotation();
        left();
        rotation();
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }

    private void saveState(Tile[][] gameTiles) {
        Tile[][] copyGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        int scoreToSave = score;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copyGameTiles[i][j] = new Tile();
                copyGameTiles[i][j].value = gameTiles[i][j].value;
            }
        }
        previousStates.push(copyGameTiles);
        previousScores.push(scoreToSave);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
                break;
            default:
                break;
        }
    }

    public boolean hasBoardChanged() {
        int scoreGameTiles = 0;
        int scorePreviousStates = 0;
        Tile[][] tiltsPreviousStates = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                scoreGameTiles += gameTiles[i][j].value;
                scorePreviousStates += tiltsPreviousStates[i][j].value;
            }
        }
        return scoreGameTiles != scorePreviousStates;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> goodMove = new PriorityQueue<>(4, Collections.reverseOrder());
        goodMove.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        goodMove.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        goodMove.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        goodMove.add(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                down();
            }
        }));
        goodMove.peek().getMove().move();
    }

    private void rotation() {
        int n = gameTiles.length;
        Tile tmp;
        for (int i = 0; i < n / 2; i++) {              //Граница -> центр
            for (int j = i; j < n - 1 - i; j++) {       //left -> right
                tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[n - j - 1][i];
                gameTiles[n - j - 1][i] = gameTiles[n - i - 1][n - j - 1];
                gameTiles[n - i - 1][n - j - 1] = gameTiles[j][n - i - 1];
                gameTiles[j][n - i - 1] = tmp;
            }
        }
    }
}
