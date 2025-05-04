package ru.academits.eliseev.arraylisthome;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();

        // Пункт 1
        try {
            ArrayList<String> fileLines = getFileLines("ArrayListHome/src/ru/academits/eliseev/cache/task1.txt");
            System.out.println("Задание 1" + newLine + "Список со строками из файла: " + fileLines);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }

        // Пункт 2
        ArrayList<Integer> numbers1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11));
        System.out.println("Задание 2" + newLine + "Исходный список: " + numbers1);
        deleteEvenNumbers(numbers1);
        System.out.println("Список с удаленными четными числами: " + numbers1);

        // Пункт 3
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 2, 4, 3, 5));
        System.out.println("Задание 3" + newLine + "Исходный список: " + numbers2 + newLine + "Список с уникальными числами: " + getUniqueElementsList(numbers2));
    }

    public static ArrayList<String> getFileLines(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> lines = new ArrayList<>();

            String line = reader.readLine();

            while (line != null) {
                lines.add(line);

                line = reader.readLine();
            }

            return lines;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static <T> ArrayList<T> getUniqueElementsList(ArrayList<T> list) {
        ArrayList<T> uniqueElements = new ArrayList<>(list.size());

        for (T element : list) {
            if (!uniqueElements.contains(element)) {
                uniqueElements.add(element);
            }
        }

        return uniqueElements;
    }
}
