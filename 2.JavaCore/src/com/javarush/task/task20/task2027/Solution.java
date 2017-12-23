package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/*
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list=detectAllWords(crossword, "home", "same","hrrpl","epga","ulmp","erpo","unpe","opre","eo","er");
        for (Word s : list) {
            System.out.println(s.toString());
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[1].length; j++) {
                    if (chars[0] == crossword[i][j]) {
                        for (int w = 2; w >= 0; w--) {
                            for (int y = 2; y >= 0; y--) {
                                int yStart = i;
                                int xStart = j;
                                int k = 0;
                                boolean motion = true;
                                while (motion) {
                                    try {
                                        //if (w == 0 && y == 0) {break;} //Ьожно без этого
                                        switch (w) {
                                            case 2:
                                                xStart++;
                                                break;
                                            case 1:
                                                xStart--;
                                                break;
                                            case 0:
                                                xStart = xStart;
                                                break;
                                        }
                                        switch (y) {
                                            case 2:
                                                yStart++;
                                                break;
                                            case 1:
                                                yStart--;
                                                break;
                                            case 0:
                                                yStart = yStart;
                                                break;
                                        }
                                        k++;
                                        if (chars[k] != crossword[yStart][xStart]) {
                                            break;
                                        }
                                        if (k == chars.length - 1) {
                                            Word word1 = new Word(word);
                                            word1.setStartPoint(j,i );
                                            word1.setEndPoint(xStart,yStart );
                                            list.add(word1);
                                        }
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        motion = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return list;
    }


    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
