package kz.platonus.task1.main;

import kz.platonus.task1.service.Printer;
import kz.platonus.task1.service.ShapeService;
import kz.platonus.task1.shape.Circle;
import kz.platonus.task1.shape.Rectangle;
import kz.platonus.task1.interfaces.Shape;
import kz.platonus.task1.shape.Square;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Rectangle(4,6));
        shapes.add(new Square(7));
        shapes.add(new Circle(7.8));
        shapes.add(new Square(7));
        shapes.add(new Circle(7.8));

        double sumOfAreas = ShapeService.getSumOfAreas(shapes);

        System.out.println("Type in which format you want to get the answer: JSON/XML/HTML ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String format = reader.readLine();

        Printer.printSumInFormat(format,sumOfAreas);
    }

}
