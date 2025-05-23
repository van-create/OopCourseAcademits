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
        int elementsCount = scanner.nextInt();

        DoubleStream squareRootsStream = IntStream
                .iterate(0, i -> i + 1)
                .mapToDouble(Math::sqrt)
                .limit(elementsCount);

        System.out.println("Квадратные корни чисел от 1 до " + elementsCount + ":");
        squareRootsStream.forEach(System.out::println);

        // Числа Фибоначчи
        System.out.println("Введите количество элементов ряда Фибоначчи:");
        int fibonacciNumbersCount = scanner.nextInt();

        IntStream fibonacciNumbersStream = Stream
                .iterate(new int[]{0, 1}, pair -> {
                    int nextFibonacciNumber = pair[0] + pair[1];
                    pair[0] = pair[1];
                    pair[1] = nextFibonacciNumber;
                    return pair;
                })
                .mapToInt(pair -> pair[0])
                .limit(fibonacciNumbersCount);

        System.out.println("Числа Фибоначчи:");
        fibonacciNumbersStream.forEach(System.out::println);
        
        scanner.close();
    }
}
