package com.javarush.task.task33.task3308;

/**
 * Created by Sfill on 18.10.2017.
 */
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@XmlType(name="shop")
@XmlRootElement
public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    //В классе Shop.Goods должен быть создан список строк names.

    @XmlType(name="goods")
    @XmlRootElement

    public static class Goods {
        //@XmlMixed
       // @XmlElementWrapper(name="goods", nillable = true)
        public List<String> names = new ArrayList<>();
    }
}
