package com.javarush.task.task36.task3608.model;

/**
 * Created by Sfill on 30.05.2017.
 */
public interface Model {
    ModelData getModelData();

    void loadUsers();

    void loadDeletedUsers();
}
