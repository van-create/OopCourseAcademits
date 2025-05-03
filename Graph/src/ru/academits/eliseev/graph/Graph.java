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

    public static void depthFisrtSearch(int startVertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");

                for (int i = graph.length - 1; i >= 0; i--) {
                    if (graph[vertex][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
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

            for (int i = 0; i < graph[startVertex].length; i++) {
                if (!visited[i] && graph[startVertex][i] == 1) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }

    public static void main(String[] args) {
        int vertices = graph.length;
        boolean[] visited = new boolean[graph.length];

        System.out.println("Обход в глубину:");
        Arrays.fill(visited, false);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                depthFisrtSearch(i, visited);
            }
        }

        System.out.println(System.lineSeparator() + "Обход в ширину:");
        Arrays.fill(visited, false);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                breadthFirstSearch(i, visited);
            }
        }
    }

}
