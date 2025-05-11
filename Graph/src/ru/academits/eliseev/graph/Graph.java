package ru.academits.eliseev.graph;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] graph;

    public Graph(int[][] array) {
        if (array == null) {
            throw new NullPointerException("Массив данных не может быть null.");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив данных не может быть пустым.");
        }

        int graphSize = 0;
        for (int[] row : array) {
            if (row != null) {
                graphSize = Math.max(row.length, graphSize);
            }
        }
        if (graphSize == 0) {
            throw new IllegalArgumentException("Массив данных не может содержать только null или пустые строки.");
        }

        graph = new int[graphSize][graphSize];

        for (int i = 0; i < graphSize; i++) {
            if (i >= array.length || array[i] == null) {
                graph[i] = new int[graphSize];
                continue;
            }

            graph[i] = Arrays.copyOf(array[i], graphSize);
        }
    }

    public void depthFirstSearchIterative(IntConsumer consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null.");
        }

        int verticesCount = graph.length;
        boolean[] visited = new boolean[verticesCount];

        Deque<Integer> stack = new LinkedList<>();

        for (int startVertex = 0; startVertex < verticesCount; startVertex++) {
            if (visited[startVertex]) {
                continue;
            }

            stack.push(startVertex);

            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                visited[vertex] = true;
                consumer.accept(vertex);

                for (int j = graph.length - 1; j >= 0; j--) {
                    if (graph[vertex][j] != 0 && !visited[j]) {
                        stack.push(j);
                    }
                }
            }
        }
    }

    public void depthFirstSearchRecursive(IntConsumer consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null.");
        }

        int verticesCount = graph.length;
        boolean[] visited = new boolean[verticesCount];

        for (int startVertex = 0; startVertex < verticesCount; startVertex++) {
            if (visited[startVertex]) {
                continue;
            }

            depthFirstSearchRecursive(startVertex, visited, consumer);
        }
    }

    private void depthFirstSearchRecursive(int startVertex, boolean[] visited, IntConsumer consumer) {
        visited[startVertex] = true;
        consumer.accept(startVertex);

        for (int i = 0; i < graph.length; i++) {
            if (graph[startVertex][i] != 0 && !visited[i]) {
                depthFirstSearchRecursive(i, visited, consumer);
            }
        }
    }

    public void breadthFirstSearch(IntConsumer consumer) {
        if (consumer == null) {
            throw new NullPointerException("Consumer не может быть null.");
        }

        int verticesCount = graph.length;
        boolean[] visited = new boolean[verticesCount];

        Queue<Integer> queue = new LinkedList<>();

        for (int startVertex = 0; startVertex < verticesCount; startVertex++) {
            if (visited[startVertex]) {
                continue;
            }

            queue.add(startVertex);
            visited[startVertex] = true;

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                consumer.accept(vertex);

                for (int i = 0; i < graph.length; i++) {
                    if (!visited[i] && graph[vertex][i] != 0) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }
    }
}