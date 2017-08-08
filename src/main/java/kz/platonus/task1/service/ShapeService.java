package kz.platonus.task1.service;

import kz.platonus.task1.interfaces.Shape;
import java.util.List;

public class ShapeService {

    public static double getSumOfAreas(List<Shape> shapes){
        return shapes.stream().map(Shape::calculateArea).mapToDouble(Double::doubleValue).sum();
    }

}
