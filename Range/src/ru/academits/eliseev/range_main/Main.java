package ru.academits.eliseev.range_main;

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
    }
}
