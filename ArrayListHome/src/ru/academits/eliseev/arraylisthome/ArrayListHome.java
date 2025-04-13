package ru.academits.eliseev.arraylisthome;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();

        // Пункт 1
        ArrayList<String> lines = new ArrayList<>();
        readNumbersFromFile("ArrayListHome/src/ru/academits/eliseev/cache/task1.txt", lines);
        System.out.println("Задание 1" + newLine + "Список со строками из файла: " + lines);

        // Пункт 2
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11));
        System.out.println("Задание 2" + newLine + "Исходный список: " + numbers);
        deleteEvenNumbers(numbers);
        System.out.println("Cписок с удаленными четными числами: " + numbers);

        // Пункт 3
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 2, 4, 3, 5));
        System.out.println("Задание 3" + newLine + "Исходный список: " + numbers1 + newLine + "Список с уникальными числами: " + getUniqueNumbersList(numbers1));
    }

    public static void readNumbersFromFile(String fileName, ArrayList<String> lines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);

                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println("Ошибка при работе с файлом: " + exception.getMessage());
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);

                i--;
            }
        }
    }

    public static ArrayList<Integer> getUniqueNumbersList(ArrayList<Integer> list) {
        ArrayList<Integer> uniqueNumbers = new ArrayList<>(list.size());

        for (Integer number : list) {
            if (!uniqueNumbers.contains(number)) {
                uniqueNumbers.add(number);
            }
        }

        return uniqueNumbers;
    }
}
