package ru.academits.eliseev.matrix_main;

import ru.academits.eliseev.vector.Vector;
import ru.academits.eliseev.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        // Проверка конструктора
        Matrix testMatrix = new Matrix(3, 3);
        System.out.println("Матрица с нулями:");
        System.out.println(testMatrix);

        // Создание матрицы 3x3 с заданными значениями
        double[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix matrix = new Matrix(array);
        System.out.println("Исходная матрица:");
        System.out.println(matrix);

        // Проверка получения размеров матрицы
        System.out.println("Размеры матрицы: " + matrix.getRowsCount() + "x" + matrix.getColumnsCount());

        // Проверка вставки вектора
        Vector newRowVector = new Vector(3);
        matrix.setRow(0, newRowVector);
        System.out.println("Измененная матрица:");
        System.out.println(matrix);

        // Проверка получения строки
        Vector rowVector = matrix.getRow(1);
        System.out.println("Вторая строка матрицы: " + rowVector);

        // Проверка получения столбца
        Vector columnVector = matrix.getColumn(2);
        System.out.println("Третий столбец матрицы: " + columnVector);

        // Проверка транспонирования матрицы
        matrix.transpose();
        System.out.println("Транспонированная матрица:");
        System.out.println(matrix);

        // Проверка умножения матрицы на число
        matrix.multiply(2);
        System.out.println("Матрица после умножения на 2:");
        System.out.println(matrix);

        // Проверка вычисления определителя
        double determinant = matrix.getDeterminant();
        System.out.println("Определитель матрицы: " + determinant);

        // Создание второй матрицы для операций сложения и вычитания
        double[][] array2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        Matrix matrix2 = new Matrix(array2);
        System.out.println("Вторая матрица:");
        System.out.println(matrix2);

        // Проверка сложения матриц
        Matrix sumMatrix = Matrix.getSum(matrix, matrix2);
        System.out.println("Сумма матриц:");
        System.out.println(sumMatrix);

        // Проверка вычитания матриц
        Matrix differenceMatrix = Matrix.getDifference(matrix, matrix2);
        System.out.println("Разность матриц:");
        System.out.println(differenceMatrix);

        // Проверка умножения матриц
        Matrix productMatrix = Matrix.getProduct(matrix, matrix2);
        System.out.println("Произведение матриц:");
        System.out.println(productMatrix);

        // Проверка умножения матрицы на вектор
        Vector vector = new Vector(new double[]{1, 1, 1});
        System.out.println("Результат умножения на вектор: " + matrix.multiplyByVector(vector));

        // Сравнение матриц
        Matrix testMatrix1 = new Matrix(array2);
        Matrix testMatrix2 = new Matrix(array2);
        System.out.println(testMatrix1.equals(testMatrix2)); // false

        // Хэш-код
        System.out.println(testMatrix1.hashCode());

        // Проверка
        double[][] array3 = {
                {9, 8, 7, 0},
                {6, 5, 4},
                {3, 2, 1},
                {0}
        };

        Vector[] vectors = {new Vector(array3[0]), new Vector(array3[1]), new Vector(array3[2]), new Vector(array3[3])};

        System.out.println(new Matrix(vectors));
    }
}
