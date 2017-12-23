package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by Sfill on 01.06.2017.
 */
public class MainModel implements Model {

    private ModelData modelData = new ModelData();

    public ModelData getModelData() {
        return modelData;
    }

    private UserService userService = new UserServiceImpl();

    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(userService.getUsersBetweenLevels(1, 100));
    }
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers( userService.getAllDeletedUsers());

    }
}
