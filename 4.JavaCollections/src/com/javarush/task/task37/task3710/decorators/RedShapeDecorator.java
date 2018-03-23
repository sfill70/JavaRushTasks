package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

/**
 * Created by Sfill on 23.03.2018.
 */
public class RedShapeDecorator extends ShapeDecorator {

   // protected Shape decoratedShape;
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }
    private void setBorderColor (Shape shape){
        System.out.println("Setting border color for "+ decoratedShape.getClass().getSimpleName()+ " to red.");
    }

    @Override
    public void draw() {
        setBorderColor(decoratedShape);
        super.draw();
    }

}
