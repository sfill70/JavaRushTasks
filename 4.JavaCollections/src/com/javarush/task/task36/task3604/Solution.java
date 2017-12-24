package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберись в коде и исправь ошибки.
Метод main не участвует в тестировании.
Все модификаторы правильные.
Имена переменных и методов не изменяйте.


Требования:
1. Исправь ошибку в методе isEmpty в классе RedBlackTree.
2. Исправь ошибку в методе rotateWithRightNode в классе RedBlackTree.
3. Исправь ошибку в методе insert в классе RedBlackTree.
4. Класс RedBlackTree должен реализовывать красно-черное дерево.

*/
public class Solution {
    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();
       // tree.insert(2);
        for (int i = 1; i <10 ; i++) {
            tree.insert(i);
        }


        System.out.println(tree.getCurrent()+" "+ tree.getCurrent().getLeft()+ " "+ tree.getCurrent().getRight()+ " "+ tree.getHeader()+ " "+tree.getHeader().getLeft()+ " "+tree.getHeader().getRight());
        tree.clear();
        System.out.println(tree.getCurrent()+" "+ tree.getCurrent().getLeft()+ " "+ tree.getCurrent().getRight()+ " "+ tree.getParent()+ " "+tree.getParent().getLeft()+ " "+tree.getParent().getRight());
    }
}
