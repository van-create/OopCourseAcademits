package ru.academits.eliseev.shapes_main;

import ru.academits.eliseev.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shape[] shapesArray = new Shape[10];

        shapesArray[0] = new Circle(5);
        shapesArray[1] = new Triangle(0, 0, 3, 0, 0, 4);
        shapesArray[2] = new Square(4);
        shapesArray[3] = new Rectangle(3, 7);
        shapesArray[4] = new Circle(2.5);
        shapesArray[5] = new Triangle(1, 1, 4, 1, 1, 5);
        shapesArray[6] = new Square(2);
        shapesArray[7] = new Rectangle(4, 6);
        shapesArray[8] = new Circle(3.5);
        shapesArray[9] = new Triangle(2, 2, 5, 2, 2, 6);

        Arrays.sort(shapesArray, new Comparator<>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.getArea(), s2.getArea());
            }
        });

        System.out.println("Информация о фигуре с максимальной площадью:" + System.lineSeparator() + System.lineSeparator());
        System.out.println(shapesArray[shapesArray.length - 1]);

        Arrays.sort(shapesArray, new Comparator<>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.getPerimeter(), s2.getPerimeter());
            }
        });

        System.out.println("Информация о фигуре со вторым по величине периметром:");
        System.out.println(shapesArray[shapesArray.length - 2]);
    }
}
