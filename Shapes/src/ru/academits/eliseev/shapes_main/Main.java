package ru.academits.eliseev.shapes_main;

import ru.academits.eliseev.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Shapes[] shapesArray = new Shapes[10];

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

        Arrays.sort(shapesArray, Comparator.comparingDouble(Shapes::getArea));

        System.out.println("Информация о фигуре с максимальной площадью:");
        System.out.printf(shapesArray[9].toString());

        System.out.println("\n\nИнформация о фигуре со второй по величине площадью:");
        System.out.printf(shapesArray[8].toString());
    }
}
