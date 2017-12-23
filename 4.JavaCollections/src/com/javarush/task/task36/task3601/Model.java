package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by Sfill on 20.12.2017.
 */
public class Model {
    Service service = new Service();
    public List<String> getStringDataList() {
        return service.getData();
    }
}
