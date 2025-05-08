package ru.academits.eliseev.tree_main;

import ru.academits.eliseev.tree.BinarySearchTree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Тест 1: Дерево с Integer (естественный порядок)
        System.out.println("Тест 1: Дерево с Integer (естественный порядок)");
        BinarySearchTree<Integer> intTree = new BinarySearchTree<>();

        // Вставка элементов
        intTree.add(50);
        intTree.add(30);
        intTree.add(70);
        intTree.add(20);
        intTree.add(40);
        intTree.add(60);
        intTree.add(80);

        System.out.println("Размер дерева: " + intTree.size()); // Ожидается: 7

        System.out.print("Рекурсивный preorder обход: ");
        intTree.traverseDepthFirstRecursive(x -> System.out.print(x + " ")); // Ожидается: 50 30 20 40 70 60 80
        System.out.println();

        System.out.print("Итеративный preorder обход: ");
        intTree.traverseDepthFirstIterative(x -> System.out.print(x + " ")); // Ожидается: 50 30 20 40 70 60 80
        System.out.println();

        System.out.print("BFS обход: ");
        intTree.traverseBreadthFirst(x -> System.out.print(x + " ")); // Ожидается: 50 30 70 20 40 60 80
        System.out.println();

        // Поиск
        System.out.println("Поиск 40: " + intTree.search(40)); // Ожидается: true
        System.out.println("Поиск 100: " + intTree.search(100)); // Ожидается: false

        // Удаление узла с двумя детьми (50)
        intTree.remove(50);
        System.out.println("Размер после удаления 50: " + intTree.size()); // Ожидается: 6

        System.out.print("Итеративный preorder после удаления 50: ");
        intTree.traverseDepthFirstIterative(x -> System.out.print(x + " ")); // Ожидается: 60 30 20 40 70 80
        System.out.println();

        // Удаление листа (20)
        intTree.remove(20);
        System.out.println("Размер после удаления 20: " + intTree.size()); // Ожидается: 5

        System.out.print("BFS после удаления 20: ");
        intTree.traverseBreadthFirst(x -> System.out.print(x + " ")); // Ожидается: 60 30 70 40 80
        System.out.println();

        // Удаление узла с одним ребёнком (70)
        intTree.remove(70);
        System.out.println("Размер после удаления 70: " + intTree.size()); // Ожидается: 4

        System.out.print("Рекурсивный preorder после удаления 70: ");
        intTree.traverseDepthFirstRecursive(x -> System.out.print(x + " ")); // Ожидается: 60 30 40 80
        System.out.println();

        // Тест 2: Дерево с String (с компаратором, поддержка null)
        System.out.println();
        System.out.println("Тест 2: Дерево с String (с компаратором, поддержка null)");

        Comparator<String> stringComparator = (s1, s2) -> {
            if (s1 == null && s2 == null) return 0;
            if (s1 == null) return -1;
            if (s2 == null) return 1;
            return s1.compareTo(s2);
        };

        BinarySearchTree<String> stringTree = new BinarySearchTree<>(stringComparator);

        // Вставка элементов
        stringTree.add("banana");
        stringTree.add("apple");
        stringTree.add("orange");
        stringTree.add(null);
        stringTree.add("grape");

        System.out.println("Размер дерева: " + stringTree.size()); // Ожидается: 5

        System.out.print("Рекурсивный preorder обход: ");
        stringTree.traverseDepthFirstRecursive(x -> System.out.print((x) + " ")); // Ожидается: banana apple null grape orange
        System.out.println();

        System.out.print("Итеративный preorder обход: ");
        stringTree.traverseDepthFirstIterative(x -> System.out.print((x) + " ")); // Ожидается: banana apple null grape orange
        System.out.println();

        System.out.print("BFS обход: ");
        stringTree.traverseBreadthFirst(x -> System.out.print((x) + " ")); // Ожидается: banana apple orange null grape
        System.out.println();

        // Поиск
        System.out.println("Поиск 'apple': " + stringTree.search("apple")); // Ожидается: true
        System.out.println("Поиск null: " + stringTree.search(null)); // Ожидается: true
        System.out.println("Поиск 'pear': " + stringTree.search("pear")); // Ожидается: false

        // Удаление узла с двумя детьми (banana)
        stringTree.remove("banana");
        System.out.println("Размер после удаления 'banana': " + stringTree.size()); // Ожидается: 4

        System.out.print("Итеративный preorder после удаления 'banana': ");
        stringTree.traverseDepthFirstIterative(x -> System.out.print((x) + " ")); // Ожидается: grape apple null orange
        System.out.println();

        // Удаление null
        stringTree.remove(null);
        System.out.println("Размер после удаления null: " + stringTree.size()); // Ожидается: 3

        System.out.print("BFS после удаления null: ");
        stringTree.traverseBreadthFirst(x -> System.out.print((x) + " ")); // Ожидается: grape apple orange
        System.out.println();

        // Тест 3: Дерево с не-Comparable типом
        System.out.println();
        System.out.println("Тест 3: Дерево с не-Comparable типом");
        BinarySearchTree<Object> nonComparableTree = new BinarySearchTree<>();

        try {
            nonComparableTree.add(new Object());
            System.out.println("Ошибка: не-Comparable тип вставлен");
        } catch (ClassCastException e) {
            System.out.println("Ожидаемая ошибка при использовании не-Comparable типа: " + e.getMessage());
        }

        // Тест 4: toString
        System.out.println("\nТест 4: toString");
        System.out.println(intTree);
        System.out.println(stringTree);
    }
}