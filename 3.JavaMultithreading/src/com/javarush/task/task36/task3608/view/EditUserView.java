package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

/**
 * Created by Sfill on 01.06.2017.
 */
public class EditUserView implements View{
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void refresh(ModelData modelData){

        System.out.println("User to be edited:");
        System.out.println("\t"+ modelData.getActiveUser());
        /*for (User user: modelData.getUsers()) {
            System.out.println("\t"+ user);
        }*/
        System.out.println("===================================================");
    }

    public void fireEventShowAllUsers(){controller.onShowAllUsers();}

    public void fireEventShowDeletedUsers(){controller.onShowAllDeletedUsers();}
}
