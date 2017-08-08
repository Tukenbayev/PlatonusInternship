package kz.platonus.task1.shape;

import kz.platonus.task1.interfaces.Shape;

public class Square implements Shape {

    private double side;

    public Square(double side){
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }
}
