package ru.academits.eliseev.tree_main;

import ru.academits.eliseev.tree.BinaryTree;
import ru.academits.eliseev.tree.Node;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Тест 1: Проверка работы с Integer
        BinaryTree<Integer> intTree = new BinaryTree<>();
        Integer[] intValues = {5, 3, 7, 2, 4, 6, 8, 3};

        // Добавление элементов
        System.out.println("Добавление целых чисел: " + Arrays.toString(intValues));
        for (Integer value : intValues) {
            intTree.add(value);
        }

        // Проверка размера
        System.out.println("Размер дерева: " + intTree.size());

        // Проверка обходов
        System.out.print("Рекурсивный preorder-обход: ");
        intTree.preorderTraversalRecursive();

        System.out.print("Итеративный preorder-обход: ");
        intTree.preorderTraversalIterative();

        System.out.print("Обход в ширину: ");
        intTree.breadthFirstTraversal();

        // Проверка поиска
        int searchValue = 4;
        Node<Integer> result = intTree.search(searchValue);
        System.out.println("Поиск значения " + searchValue + ": " +
                (result != null ? "Найдено (" + result.getData() + ")" : "Не найдено"));

        // Проверка удаления узла с двумя детьми (3)
        System.out.println("\nУдаление значения 3 (узел с двумя детьми)");
        intTree.remove(3);

        System.out.println("Размер дерева после удаления: " + intTree.size());

        System.out.print("Итеративный preorder-обход после удаления: ");
        intTree.preorderTraversalIterative();

        System.out.print("Обход в ширину после удаления: ");
        intTree.breadthFirstTraversal();

        // Проверка удаления листового узла (8)
        System.out.println("\nУдаление значения 8 (листовой узел)");
        intTree.remove(8);

        System.out.println("Размер дерева после удаления: " + intTree.size());

        System.out.print("Итеративный preorder-обход после удаления: ");
        intTree.preorderTraversalIterative();

        // Проверка удаления узла с одним ребенком (7)
        System.out.println("\nУдаление значения 7 (узел с одним ребенком)");
        intTree.remove(7);

        System.out.println("Размер дерева после удаления: " + intTree.size());

        System.out.print("Итеративный preorder-обход после удаления: ");
        intTree.preorderTraversalIterative();

        // Проверка поиска отсутствующего элемента
        searchValue = 10;
        result = intTree.search(searchValue);
        System.out.println("Поиск значения " + searchValue + ": " +
                (result != null ? "Найдено (" + result.getData() + ")" : "Не найдено"));

        // Тест 2: Проверка работы с String
        BinaryTree<String> stringTree = new BinaryTree<>();
        String[] stringValues = {"собака", "кошка", "слон", "медведь", "лиса", "кошка"};

        System.out.println("\nДобавление строк: " + Arrays.toString(stringValues));
        for (String value : stringValues) {
            stringTree.add(value);
        }

        System.out.println("Размер дерева: " + stringTree.size());

        System.out.print("Итеративный preorder-обход: ");
        stringTree.preorderTraversalIterative();

        System.out.print("Обход в ширину: ");
        stringTree.breadthFirstTraversal();

        // Проверка поиска строки
        String searchString = "кошка";
        Node<String> stringResult = stringTree.search(searchString);
        System.out.println("Поиск значения \"" + searchString + "\": " +
                (stringResult != null ? "Найдено (" + stringResult.getData() + ")" : "Не найдено"));

        // Тест 3: Проверка пустого дерева
        BinaryTree<Integer> emptyTree = new BinaryTree<>();
        System.out.println("\nПроверка пустого дерева:");
        System.out.println("Размер пустого дерева: " + emptyTree.size());

        System.out.print("Итеративный preorder-обход пустого дерева: ");
        emptyTree.preorderTraversalIterative();

        System.out.print("Обход в ширину пустого дерева: ");
        emptyTree.breadthFirstTraversal();
    }
}