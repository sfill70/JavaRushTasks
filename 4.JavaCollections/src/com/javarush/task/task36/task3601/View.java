package com.javarush.task.task36.task3601;

/**
 * Created by Sfill on 20.12.2017.
 */
public class View {
Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
