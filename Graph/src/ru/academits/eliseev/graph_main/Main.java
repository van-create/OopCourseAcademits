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
        graph.depthFirstSearchIterative(vertex -> System.out.print(vertex + " "));
        System.out.println("\n");

        // Тестирование рекурсивного DFS
        System.out.println("Рекурсивный поиск в глубину:");
        graph.depthFirstSearchRecursive(vertex -> System.out.print(vertex + " "));
        System.out.println("\n");

        // Тестирование BFS
        System.out.println("Поиск в ширину:");
        graph.breadthFirstSearch(vertex -> System.out.print(vertex + " "));
        System.out.println();
    }
}
