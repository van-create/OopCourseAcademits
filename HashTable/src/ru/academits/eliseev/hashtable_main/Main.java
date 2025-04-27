package ru.academits.eliseev.hashtable_main;

import ru.academits.eliseev.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        // Тест 1: Проверка конструктора по умолчанию и метода isEmpty
        HashTable<Integer> table1 = new HashTable<>();
        System.out.println("Тест 1: Пустая таблица, isEmpty = " + table1.isEmpty()); // Ожидается: true
        System.out.println("Размер: " + table1.size()); // Ожидается: 0

        // Тест 2: Добавление элементов и проверка размера
        table1.add(1);
        table1.add(2);
        table1.add(3);
        System.out.println("Тест 2: После добавления 3 элементов, размер = " + table1.size()); // Ожидается: 3
        System.out.println("isEmpty = " + table1.isEmpty()); // Ожидается: false

        // Тест 3: Проверка метода contains
        System.out.println("Тест 3: Содержит 2 = " + table1.contains(2)); // Ожидается: true
        System.out.println("Содержит 5 = " + table1.contains(5)); // Ожидается: false
        System.out.println("Содержит null = " + table1.contains(null)); // Ожидается: false

        // Тест 4: Проверка добавления null
        table1.add(null);
        System.out.println("Тест 4: После добавления null, размер = " + table1.size()); // Ожидается: 4
        System.out.println("Содержит null = " + table1.contains(null)); // Ожидается: true

        // Тест 5: Проверка итератора
        System.out.print("Тест 5: Элементы через итератор: ");

        for (Integer integer : table1) {
            System.out.print(integer + " ");
        }

        System.out.println(); // Ожидается: 1 2 3 null (порядок может варьироваться)

        // Тест 6: Проверка ConcurrentModificationException
        try {
            Iterator<Integer> iterator = table1.iterator();

            table1.add(4);

            iterator.next();
            System.out.println("Тест 6: Ошибка ConcurrentModificationException не выброшена");
        } catch (ConcurrentModificationException e) {
            System.out.println("Тест 6: ConcurrentModificationException выброшена, как ожидалось");
        }

        // Тест 7: Проверка NoSuchElementException
        Iterator<Integer> iterator = table1.iterator();

        try {
            while (iterator.hasNext()) {
                iterator.next();
            }

            iterator.next();

            System.out.println("Тест 7: Ошибка NoSuchElementException не выброшена");
        } catch (NoSuchElementException e) {
            System.out.println("Тест 7: NoSuchElementException выброшена, как ожидалось");
        }

        // Тест 8: Проверка toArray
        Object[] array = table1.toArray();
        System.out.println("Тест 8: toArray: " + Arrays.toString(array)); // Ожидается: [1, 2, 3, 4, null] (порядок может варьироваться)

        // Тест 9: Проверка toArray(T[])
        Integer[] intArray = new Integer[5];
        table1.toArray(intArray);
        System.out.println("Тест 9: toArray(T[]): " + Arrays.toString(intArray)); // Ожидается: [1, 2, 3, 4, null] (порядок может варьироваться)

        // Тест 10: Проверка remove
        boolean removed = table1.remove(2);

        System.out.println("Тест 10: Удаление 2, результат = " + removed + ", размер = " + table1.size()); // Ожидается: true, 3
        System.out.println("Содержит 2 = " + table1.contains(2)); // Ожидается: false

        // Тест 11: Проверка конструктора с коллекцией
        ArrayList<Integer> source = new ArrayList<>(Arrays.asList(10, 20, 30, 20));
        HashTable<Integer> table2 = new HashTable<>(source);

        System.out.println("Тест 11: Конструктор с коллекцией, размер = " + table2.size()); // Ожидается: 3
        System.out.println("Содержит 20 = " + table2.contains(20)); // Ожидается: true

        // Тест 12: Проверка containsAll
        System.out.println("Тест 12: containsAll([10, 20]) = " + table2.containsAll(Arrays.asList(10, 20))); // Ожидается: true
        System.out.println("containsAll([10, 40]) = " + table2.containsAll(Arrays.asList(10, 40))); // Ожидается: false

        // Тест 13: Проверка addAll
        boolean added = table2.addAll(Arrays.asList(40, 50));

        System.out.println("Тест 13: addAll([40, 50]), результат = " + added + ", размер = " + table2.size()); // Ожидается: true, 5

        // Тест 14: Проверка removeAll
        boolean removedAll = table2.removeAll(Arrays.asList(10, 20));

        System.out.println("Тест 14: removeAll([10, 20]), результат = " + removedAll + ", размер = " + table2.size()); // Ожидается: true, 3
        System.out.println("Содержит 10 = " + table2.contains(10)); // Ожидается: false

        // Тест 15: Проверка retainAll
        boolean retained = table2.retainAll(Arrays.asList(30, 40));

        System.out.println("Тест 15: retainAll([30, 40]), результат = " + retained + ", размер = " + table2.size()); // Ожидается: true, 2
        System.out.println("Содержит 50 = " + table2.contains(50)); // Ожидается: false

        // Тест 16: Проверка clear
        table2.clear();
        //noinspection ConstantValue
        System.out.println("Тест 16: После clear, размер = " + table2.size() + ", isEmpty = " + table2.isEmpty()); // Ожидается: 0, true

        // Тест 17: Проверка конструктора с вместимостью
        HashTable<Integer> table3 = new HashTable<>(5);
        table3.add(1);
        System.out.println("Тест 17: Конструктор с вместимостью, размер = " + table3.size()); // Ожидается: 1
    }
}