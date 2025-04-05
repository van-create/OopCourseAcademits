package ru.academits.eliseev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        String newLine = System.lineSeparator();

        // Пункт 1
        ArrayList<String> fileRows = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("/Users/vanya/IdeaProjects/OopCourseAcademits/ArrayListHome/src/ru/academits/eliseev/cache/task1.txt"))) {
            while (scanner.hasNextLine()) {
                fileRows.add(scanner.nextLine());
            }
        }

        System.out.println("Задание 1" + newLine + "Список со строками из файла: " + fileRows);

        // Пункт 2
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println("Задание 2" + newLine + "Исходный список: " + numbers);

        for (int i = 0; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);

            if (currentNumber % 2 == 0) {
                numbers.remove(Integer.valueOf(currentNumber));
            }
        }

        System.out.println("Полученный список: " + numbers);

        // Пункт 3
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 2, 4, 3, 5));
        ArrayList<Integer> numbers1Set = new ArrayList<>();

        for (int number : numbers1) {
            if (!numbers1Set.contains(number)) {
                numbers1Set.add(number);
            }
        }

        System.out.println("Задание 3" + newLine + "Исходный список: " + numbers1 + newLine + "Полученный список: " + numbers1Set);
    }
}
