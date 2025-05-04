package ru.academits.eliseev.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    static final int[][] graph = {
            {0, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0}
    };
    final static int verticesCount = graph.length;

    public static void depthFirstSearchIterative(int startVertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");

                for (int i = 0; i < verticesCount; i++) {
                    if (graph[vertex][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    public static void depthFirstSearchRecursive(int startVertex, boolean[] visited) {
        visited[startVertex] = true;
        System.out.print(startVertex + " ");

        for (int i = verticesCount - 1; i >= 0; i--) {
            if (graph[startVertex][i] == 1 && !visited[i]) {
                depthFirstSearchRecursive(i, visited);
            }
        }
    }

    public static void breadthFirstSearch(int startVertex, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int i = 0; i < verticesCount; i++) {
                if (!visited[i] && graph[startVertex][i] == 1) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }

    public static void main(String[] args) {
        boolean[] visited = new boolean[verticesCount];

        System.out.println("Обход в глубину циклом:");

        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                depthFirstSearchIterative(i, visited);
            }
        }

        Arrays.fill(visited, false);

        System.out.println(System.lineSeparator() + "Обход в глубину рекурсией:");

        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                depthFirstSearchRecursive(i, visited);
            }
        }

        Arrays.fill(visited, false);

        System.out.println(System.lineSeparator() + "Обход в ширину:");

        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                breadthFirstSearch(i, visited);
            }
        }
    }

}
