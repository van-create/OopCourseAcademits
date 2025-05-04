package ru.academits.eliseev.lambdas_task2;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Поток корней чисел
        System.out.println("Введите количество элементов, корни которых нужно вычислить: ");
        int numberOfElements = scanner.nextInt();

        DoubleStream squareRoots = IntStream.iterate(1, i -> i + 1).mapToDouble(Math::sqrt).limit(numberOfElements);

        System.out.println("Квадратные корни чисел от 1 до " + numberOfElements + ":");
        squareRoots.forEach(System.out::println);

        // Числа Фибоначчи
        System.out.println("Введите количество элементов ряда Фибоначчи: ");
        numberOfElements = scanner.nextInt();

        Stream<Integer> fibonacciStream = Stream
                .iterate(new int[]{0, 1}, pair -> new int[]{pair[1], pair[0] + pair[1]})
                .map(pair -> pair[0])
                .limit(numberOfElements);

        System.out.println("Числа Фибоначчи:");
        fibonacciStream.forEach(System.out::println);
    }
}
