package ru.academits.eliseev.graph_main;

import ru.academits.eliseev.graph.Graph;

public class Main {
    public static void main(String[] args) {
        int[][] array = {
                {0, 1, 1, 0, 0, 0}, // 0 -> 1, 2
                {1, 0, 0, 1, 0, 0}, // 1 -> 0, 3
                {1, 0, 0, 0, 0, 0}, // 2 -> 0
                {0, 1, 0, 0, 1, 0}, // 3 -> 1, 4
                {0, 0, 0, 1, 0, 0}, // 4 -> 3
                {0, 0, 0, 0, 0, 0}  // 5 -> (изолирована)
        };

        Graph graph = new Graph(array);

        // Тестирование итеративного DFS
        System.out.println("Итеративный поиск в глубину:");
        graph.traverseDepthFirstIterative(vertex -> System.out.print(vertex + " "));
        System.out.println("\n");

        // Тестирование рекурсивного DFS
        System.out.println("Рекурсивный поиск в глубину:");
        graph.traverseDepthFirstRecursive(vertex -> System.out.print(vertex + " "));
        System.out.println("\n");

        // Тестирование BFS
        System.out.println("Поиск в ширину:");
        graph.traverseBreadthFirst(vertex -> System.out.print(vertex + " "));
        System.out.println();
    }
}
