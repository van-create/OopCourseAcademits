package ru.academits.eliseev.list_main;

import ru.academits.eliseev.list.SinglyLinkedList;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        String newLine = System.lineSeparator();

        // Тест 1
        System.out.println("Тест 1: Операции с пустым списком");

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        System.out.println("Длина пустого списка: " + list1.getLength()); // 0
        System.out.println("Пустой список в виде строки: " + list1); // "[]"

        try {
            Integer value = list1.getFirst();
            System.out.println("Тест 1 провален: Должно выбросить исключение для пустого списка. Полученное значение: " + value);
        } catch (NoSuchElementException e) {
            System.out.println("Тест 1 пройден: Корректно выбрасывает исключение для getFirst на пустом списке");
        }

        // Тест 3
        System.out.println(newLine + "Тест 3: Конструктор из массива и получение");

        Integer[] numbers = {1, 2, 3, 4, 5};
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<>(numbers);

        System.out.println("Длина: " + list3.getLength()); // 5
        System.out.println("Элемент по индексу 2: " + list3.get(2)); // 3
        System.out.println("В виде строки: " + list3); // [1, 2, 3, 4, 5]

        // Тест 4
        System.out.println(newLine + "Тест 4: Операции добавления");

        SinglyLinkedList<Character> list4 = new SinglyLinkedList<>();
        list4.add('А');
        list4.addFirst('Б');
        list4.add(1, 'В');

        System.out.println("Длина: " + list4.getLength()); // 3
        System.out.println("В виде строки: " + list4); // [Б, В, А]

        // Тест 5
        System.out.println(newLine + "Тест 5: Операции удаления");

        SinglyLinkedList<Integer> list5 = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4});

        int removedFirst = list5.removeFirst();
        System.out.println("Удаленный первый элемент: " + removedFirst); // 1

        int removedAtIndex = list5.remove(1);
        System.out.println("Удаленный элемент по индексу 1: " + removedAtIndex); // 3

        boolean isRemoved = list5.remove(Integer.valueOf(2));
        System.out.println("Удаление значения 2: " + isRemoved); // true

        System.out.println("В виде строки: " + list5); // [4]

        // Тест 6
        System.out.println(newLine + "Тест 6: Операция замены");

        SinglyLinkedList<String> list6 = new SinglyLinkedList<>(new String[]{"а", "б", "в"});
        String oldValue = list6.set(1, "г");

        System.out.println("Старое значение: " + oldValue); // б
        System.out.println("В виде строки: " + list6); // [а, г, в]

        // Тест 7
        System.out.println(newLine + "Тест 7: Операция разворота");

        SinglyLinkedList<Integer> list7 = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        list7.reverse();

        System.out.println("В виде строки: " + list7); // [3, 2, 1]

        // Тест 8
        System.out.println(newLine + "Тест 8: Операция копирования");

        SinglyLinkedList<Integer> list8 = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        SinglyLinkedList<Integer> copy = list8.copy();

        System.out.println("Оригинал: " + list8);
        System.out.println("Копия: " + copy);
        System.out.println("Равны: " + list8.equals(copy)); // true

        // Тест 9
        System.out.println(newLine + "Тест 9: Обработка исключений");

        try {
            new SinglyLinkedList<>(new String[]{"а", null, "в"});
            System.out.println("Тест 9.1 провален: Должно выбросить NullPointerException");
        } catch (NullPointerException e) {
            System.out.println("Тест 9.1 пройден: Корректно выбрасывает NullPointerException для null элементов");
        }

        try {
            SinglyLinkedList<Integer> list10 = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
            list10.get(5);
            System.out.println("Тест 9.2 провален: Должно выбросить IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Тест 9.2 пройден: Корректно выбрасывает IndexOutOfBoundsException для неверного индекса");
        }
    }
}
