package com.javarush.task.task36.task3608.model;

/**
 * Created by Sfill on 30.05.2017.
 */

import com.javarush.task.task36.task3608.bean.User;

import java.util.LinkedList;
import java.util.List;

public class FakeModel implements Model {

    private ModelData modelData = new ModelData();

    public ModelData getModelData() {
        return modelData;
    }

    public void loadUsers() {
        List users=new LinkedList<>();
        users.add(new User("A", 1, 1));
        users.add(new User("B", 2, 1));
        modelData.setUsers(users);
    }
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

}
