package ru.academits.eliseev.array_list_main;

import java.util.*;

import ru.academits.eliseev.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тестирование ArrayList");

        // Тест 1: Проверка конструктора по умолчанию
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Тест 1: Конструктор по умолчанию, size = " + list.size() + ", isEmpty = " + list.isEmpty());
        // Ожидаемый результат: size = 0, isEmpty = true

        // Тест 2: Проверка конструктора с начальной ёмкостью
        ArrayList<Integer> list2 = new ArrayList<>(5);
        System.out.println("Тест 2: Конструктор с ёмкостью 5, size = " + list2.size() + ", isEmpty = " + list2.isEmpty());
        // Ожидаемый результат: size = 0, isEmpty = true

        // Тест 3: Проверка конструктора с коллекцией
        list2.add(1);
        list2.add(2);
        list2.add(3);

        ArrayList<Integer> list3 = new ArrayList<>(list2);
        System.out.println("Тест 3: Конструктор с коллекцией, список = " + list3);
        // Ожидаемый результат: [1, 2, 3]

        // Тест 4: Проверка метода add(E element)
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Тест 4: Добавление элементов, список = " + list);
        // Ожидаемый результат: [10, 20, 30]

        // Тест 5: Проверка метода add(int index, E element)
        list.add(1, 15);
        System.out.println("Тест 5: Добавление по индексу 1, список = " + list);
        // Ожидаемый результат: [10, 15, 20, 30]

        // Тест 6: Проверка метода get(int index)
        System.out.println("Тест 6: Получение элемента по индексу 2, элемент = " + list.get(2));
        // Ожидаемый результат: 20

        // Тест 7: Проверка метода set(int index, E element)
        Integer oldValue = list.set(2, 25);
        System.out.println("Тест 7: Замена элемента по индексу 2, старое значение = " + oldValue + ", список = " + list);
        // Ожидаемый результат: oldValue = 20, список = [10, 15, 25, 30]

        // Тест 8: Проверка метода remove(int index)
        Integer removed = list.remove(1);
        System.out.println("Тест 8: Удаление элемента по индексу 1, удалённый элемент = " + removed + ", список = " + list);
        // Ожидаемый результат: removed = 15, список = [10, 25, 30]

        // Тест 9: Проверка метода remove(Object object)
        boolean isRemoved1 = list.remove(Integer.valueOf(25));
        System.out.println("Тест 9: Удаление элемента 25, результат = " + isRemoved1 + ", список = " + list);
        // Ожидаемый результат: true, список = [10, 30]

        // Тест 10: Проверка метода contains(Object object)
        System.out.println("Тест 10: Проверка наличия элемента 10 = " + list.contains(10));
        System.out.println("Тест 10: Проверка наличия элемента 99 = " + list.contains(99));
        // Ожидаемый результат: true, false

        // Тест 11: Проверка метода indexOf(Object object)
        System.out.println("Тест 11: Индекс элемента 30 = " + list.indexOf(30));
        System.out.println("Тест 11: Индекс элемента 99 = " + list.indexOf(99));
        // Ожидаемый результат: 1, -1

        // Тест 12: Проверка метода lastIndexOf(Object object)
        list.add(10);
        System.out.println("Тест 12: Последний индекс элемента 10 = " + list.lastIndexOf(10));
        // Ожидаемый результат: 2

        // Тест 13: Проверка метода addAll(Collection<? extends E> collection)
        ArrayList<Integer> toAdd = new ArrayList<>();
        toAdd.add(40);
        toAdd.add(50);

        list.addAll(toAdd);

        System.out.println("Тест 13: Добавление коллекции, список = " + list);
        // Ожидаемый результат: [10, 30, 10, 40, 50]

        // Тест 14: Проверка метода addAll(int index, Collection<? extends E> collection)
        list.addAll(2, toAdd);
        System.out.println("Тест 14: Добавление коллекции по индексу 2, список = " + list);
        // Ожидаемый результат: [10, 30, 40, 50, 10, 40, 50]

        // Тест 15: Проверка метода containsAll(Collection<?> collection)
        System.out.println("Тест 15: Проверка наличия коллекции [40, 50] = " + new HashSet<>(list).containsAll(toAdd)); // Преобразовал в сет во избежание варнинга
        // Ожидаемый результат: true

        // Тест 16: Проверка метода removeAll(Collection<?> collection)
        boolean isRemoved2 = list.removeAll(toAdd);
        System.out.println("Тест 16: Удаление всех элементов коллекции, результат = " + isRemoved2 + ", список = " + list);
        // Ожидаемый результат: true, список = [10, 30, 10]

        // Тест 17: Проверка метода retainAll(Collection<?> collection)
        ArrayList<Integer> retainList = new ArrayList<>();
        retainList.add(10);
        boolean isRetained = list.retainAll(retainList);
        System.out.println("Тест 17: Оставить только элементы коллекции [10], результат = " + isRetained + ", список = " + list);
        // Ожидаемый результат: true, список = [10, 10]

        // Тест 18: Проверка метода clear()
        list.clear();
        //noinspection ConstantValue
        System.out.println("Тест 18: Очистка списка, size = " + list.size() + ", isEmpty = " + list.isEmpty());
        // Ожидаемый результат: size = 0, isEmpty = true

        // Тест 19: Проверка метода toArray()
        list.add(1);
        list.add(2);

        Object[] array = list.toArray();
        System.out.println("Тест 19: Преобразование в массив, результат = " + Arrays.toString(array));
        // Ожидаемый результат: [1, 2]

        // Тест 20: Проверка метода toArray(T[] array)
        Integer[] targetArray = new Integer[3];
        Integer[] resultArray = list.toArray(targetArray);

        System.out.println("Тест 20: Преобразование в массив Integer[], результат = " + Arrays.toString(resultArray));
        // Ожидаемый результат: [1, 2, null]

        // Тест 21: Проверка метода ensureCapacity(int minCapacity)
        list.ensureCapacity(20);
        System.out.println("Тест 21: Увеличение ёмкости до 20, size = " + list.size());
        // Ожидаемый результат: size = 2 (ёмкость не влияет на size)

        // Тест 22: Проверка метода trimToSize()
        list.trimToSize();
        System.out.println("Тест 22: Уменьшение ёмкости до размера, size = " + list.size());
        // Ожидаемый результат: size = 2

        // Тест 23: Проверка итератора
        list.add(3);

        System.out.print("Тест 23: Итерация по списку: ");

        for (Integer item : list) {
            System.out.print(item + " ");
        }

        System.out.println();
        // Ожидаемый результат: 1 2 3

        // Тест 24: Проверка исключения IndexOutOfBoundsException
        try {
            Integer previousElement = list.get(10);

            System.out.println("Тест 24: Ошибка - должно быть исключение IndexOutOfBoundsException. Получено " + previousElement);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Тест 24: Успех - поймано IndexOutOfBoundsException");
        }

        // Тест 25: Проверка ConcurrentModificationException в итераторе
        try {
            Iterator<Integer> iterator = list.iterator();

            list.add(4);

            iterator.next();

            System.out.println("Тест 25: Ошибка - должно быть исключение ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            System.out.println("Тест 25: Успех - поймано ConcurrentModificationException");
        }

        // Тест 26: Проверка NoSuchElementException в итераторе
        try {
            Iterator<Integer> iterator = list.iterator();

            while (iterator.hasNext()) {
                iterator.next();
            }

            iterator.next();

            System.out.println("Тест 26: Ошибка - должно быть исключение NoSuchElementException");
        } catch (NoSuchElementException e) {
            System.out.println("Тест 26: Успех - поймано NoSuchElementException");
        }

        // Тест 27: Проверка конструктора с отрицательной ёмкостью
        try {
            new ArrayList<Integer>(-1);

            System.out.println("Тест 27: Ошибка - должно быть исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Тест 27: Успех - поймано IllegalArgumentException");
        }

        // Тест 28: Проверка null коллекции в addAll
        try {
            list.addAll(null);

            System.out.println("Тест 28: Ошибка - должно быть исключение NullPointerException");
        } catch (NullPointerException e) {
            System.out.println("Тест 28: Успех - поймано NullPointerException");
        }
    }
}
