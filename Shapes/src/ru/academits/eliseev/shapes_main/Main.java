package ru.academits.eliseev.shapes_main;

import ru.academits.eliseev.shapes_comparators.ShapeAreaComparator;
import ru.academits.eliseev.shapes_comparators.ShapePerimeterComparator;
import ru.academits.eliseev.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapesArray = {
                new Circle(5),
                new Triangle(0, 0, 3, 0, 0, 4),
                new Square(4),
                new Rectangle(3, 7),
                new Circle(2.5),
                new Triangle(1, 1, 4, 1, 1, 5),
                new Square(2),
                new Rectangle(4, 6),
                new Circle(3.5),
                new Triangle(2, 2, 5, 2, 2, 6)
        };

        Arrays.sort(shapesArray, new ShapeAreaComparator());

        System.out.println("Информация о фигуре с максимальной площадью:" + System.lineSeparator() + System.lineSeparator());
        System.out.println(shapesArray[shapesArray.length - 1]);

        Arrays.sort(shapesArray, new ShapePerimeterComparator());

        System.out.println("Информация о фигуре со вторым по величине периметром:");
        System.out.println(shapesArray[shapesArray.length - 2]);
    }
}
