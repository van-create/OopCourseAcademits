package ru.academits.eliseev.range_main;

import java.util.Arrays;
import java.util.Scanner;
import ru.academits.eliseev.range.Range;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальное число диапазона: ");
        double from = scanner.nextDouble();

        System.out.print("Введите конечное число диапазона: ");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.println("Начальное число диапазона: " + range.getFrom());
        System.out.println("Конечное число диапазона: " + range.getTo());
        System.out.println("Длина диапазона: " + range.getLength());

        System.out.print("Введите новое начальное число диапазона: ");
        range.setFrom(scanner.nextDouble());

        System.out.print("Введите новое конечное число диапазона: ");
        range.setTo(scanner.nextDouble());

        System.out.print("Введите число для проверки нахождения его в диапазоне: ");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число находится в диапазоне.");
        } else {
            System.out.println("Число находится вне диапазона.");
        }

        // Range*

        System.out.print("Введите начальное число первого диапазона: ");
        double from1 = scanner.nextDouble();

        System.out.print("Введите конечное число второго диапазона: ");
        double to1 = scanner.nextDouble();

        System.out.print("Введите начальное число второго диапазона: ");
        double from2 = scanner.nextDouble();

        System.out.print("Введите конечное число второго диапазона: ");
        double to2 = scanner.nextDouble();

        Range range1 = new Range(from1, to1);
        Range range2 = new Range(from2, to2);

        System.out.println("Пересечение: " + range1.getIntersection(range2));
        System.out.println("Объединение: " + Arrays.toString((range1.getUnion(range2))));
        System.out.println("Разность: " + Arrays.toString(range1.getDifference(range2)));
    }
}
