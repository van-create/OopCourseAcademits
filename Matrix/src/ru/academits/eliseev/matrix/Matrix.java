package ru.academits.eliseev.matrix;

import ru.academits.eliseev.vector.Vector;

import java.util.Arrays;
import java.util.StringJoiner;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть положительным числом: rowsCount = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть положительным числом: columnsCount = " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Копируемая матрица не может быть null.");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Массив данных не может быть null.");
        }

        int columnsCount = 0;

        for (double[] row : array) {
            if (row != null) {
                columnsCount = Math.max(row.length, columnsCount);
            }
        }

        if (array.length == 0 || columnsCount == 0) {
            throw new IllegalArgumentException("Массив не может быть пустой.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив векторов не может быть null.");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым.");
        }

        int columnsCount = 0;

        for (Vector vector : vectors) {
            if (vector == null) {
                throw new NullPointerException("Элемент массива векторов не может быть null.");
            }

            columnsCount = Math.max(vector.getSize(), columnsCount);
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            if (vectors[i].getSize() < columnsCount) {
                rows[i] = new Vector(columnsCount);

                for (int j = 0; j < vectors[i].getSize(); j++) {
                    rows[i].setComponent(j, vectors[i].getComponent(j));
                }
            } else {
                rows[i] = vectors[i];
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вставляемый вектор не может быть null.");
        }

        int rowsCount = rows.length;
        int columnsCount = rows[0].getSize();
        int vectorSize = vector.getSize();

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: index = " + index + "; Допустимый диапазон: от 0 до " + (rowsCount - 1) + "(включительно)");
        }

        if (vectorSize != columnsCount) {
            throw new IllegalArgumentException("Вставляемый вектор должен иметь столько же компонентов, сколько столбцов в матрице. Количество компонентов в данном векторе: " + vectorSize + "; Количество столбцов в матрице: " + columnsCount);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getRow(int index) {
        int rowsCount = rows.length;

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: index = " + index + "; Допустимый диапазон: от 0 до " + (rowsCount - 1) + "(включительно)");
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        int rowsCount = rows.length;
        int columnsCount = rows[0].getSize();

        if (index < 0 || index >= columnsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: index = " + index + "; Допустимый диапазон: от 0 до " + (columnsCount - 1) + "(включительно)");
        }

        Vector resultVector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            resultVector.setComponent(i, rows[i].getComponent(index));
        }

        return resultVector;
    }

    public void transpose() {
        int rowsCount = rows[0].getSize();
        Vector[] transposedMatrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            transposedMatrix[i] = getColumn(i);
        }

        rows = transposedMatrix;
    }

    public void multiply(double number) {
        for (Vector row : rows) {
            row.multiply(number);
        }
    }

    private static double[][] getSubmatrix(double[][] matrix, int column) {
        int size = matrix.length;
        double[][] minor = new double[size - 1][size - 1];

        for (int matrixRow = 1, minorRow = 0; matrixRow < size; matrixRow++) {
            for (int matrixColumn = 0, minorColumn = 0; matrixColumn < size; matrixColumn++) {
                if (matrixColumn == column) {
                    continue;
                }

                minor[minorRow][minorColumn] = matrix[matrixRow][matrixColumn];

                minorColumn++;
            }

            minorRow++;
        }

        return minor;
    }

    private double getDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;

        for (int i = 0; i < matrix.length; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * getDeterminant(getSubmatrix(matrix, i));
        }

        return determinant;
    }

    public double getDeterminant() {
        int rowsCount = rows.length;
        int columnsCount = rows[0].getSize();

        if (rowsCount != columnsCount) {
            throw new IllegalStateException("Матрица должна быть квадратной. Размеры данной матрицы: " + rowsCount + "x" + columnsCount);
        }

        double[][] matrix = new double[rowsCount][rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < rowsCount; j++) {
                matrix[i][j] = rows[i].getComponent(j);
            }
        }

        return getDeterminant(matrix);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (Vector row : rows) {
            joiner.add(String.valueOf(row));
        }

        return joiner.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) object;

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(rows);

        return hash;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не может быть null.");
        }

        int rowsCount = rows.length;
        int columnsCount = rows[0].getSize();

        if (vector.getSize() != columnsCount) {
            throw new IllegalArgumentException("Вектор должен иметь столько компонентов, сколько столбцов в матрице. Количество компонентов вектора: " + vector.getSize() + "; Количество столбцов в матрице: " + columnsCount);
        }

        Vector resultVector = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            double currentComponent = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                currentComponent += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, currentComponent);
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Прибавляемая матрица не может быть null.");
        }

        int thisMatrixRowsCount = rows.length;
        int thisMatrixColumnsCount = rows[0].getSize();
        int otherMatrixRowsCount = matrix.rows.length;
        int otherMatrixColumnsCount = matrix.rows[0].getSize();

        if (thisMatrixRowsCount != otherMatrixRowsCount || thisMatrixColumnsCount != otherMatrixColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров. Размеры текущей матрицы: " + thisMatrixRowsCount + "x" + thisMatrixColumnsCount + "; Размеры прибавляемой матрицы: " + otherMatrixRowsCount + "x" + otherMatrixColumnsCount);
        }

        for (int i = 0; i < thisMatrixRowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Вычитаемая матрица не может быть null.");
        }

        int thisMatrixRowsCount = rows.length;
        int thisMatrixColumnsCount = rows[0].getSize();
        int otherMatrixRowsCount = matrix.rows.length;
        int otherMatrixColumnsCount = matrix.rows[0].getSize();

        if (thisMatrixRowsCount != otherMatrixRowsCount || thisMatrixColumnsCount != otherMatrixColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров. Размеры текущей матрицы: " + thisMatrixRowsCount + "x" + thisMatrixColumnsCount + "; Размеры вычитаемой матрицы: " + otherMatrixRowsCount + "x" + otherMatrixColumnsCount);
        }

        for (int i = 0; i < thisMatrixRowsCount; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Матрица 1 не может быть null.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Матрица 2 не может быть null.");
        }

        int matrix1RowsCount = matrix1.rows.length;
        int matrix1ColumnsCount = matrix1.rows[0].getSize();
        int matrix2RowsCount = matrix2.rows.length;
        int matrix2ColumnsCount = matrix2.rows[0].getSize();

        if (matrix1RowsCount != matrix2RowsCount || matrix1ColumnsCount != matrix2ColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров. Размеры первой матрицы: " + matrix1RowsCount + "x" + matrix1ColumnsCount + "; Размеры второй матрицы: " + matrix2RowsCount + "x" + matrix2ColumnsCount);
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Матрица 1 не может быть null.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Матрица 2 не может быть null.");
        }

        int matrix1RowsCount = matrix1.rows.length;
        int matrix1ColumnsCount = matrix1.rows[0].getSize();
        int matrix2RowsCount = matrix2.rows.length;
        int matrix2ColumnsCount = matrix2.rows[0].getSize();

        if (matrix1RowsCount != matrix2RowsCount || matrix1ColumnsCount != matrix2ColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров. Размеры первой матрицы: " + matrix1RowsCount + "x" + matrix1ColumnsCount + "; Размеры второй матрицы: " + matrix2RowsCount + "x" + matrix2ColumnsCount);
        }

        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("Матрица 1 не может быть null.");
        }

        if (matrix2 == null) {
            throw new NullPointerException("Матрица 2 не может быть null.");
        }

        int matrix1RowsCount = matrix1.rows.length;
        int matrix1ColumnsCount = matrix1.rows[0].getSize();
        int matrix2RowsCount = matrix2.rows.length;
        int matrix2ColumnsCount = matrix2.rows[0].getSize();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Число столбцов первой матрицы (" + matrix1ColumnsCount + ") должно равняться числу строк второй матрицы (" + matrix2RowsCount + ").");
        }

        Vector[] resultRows = new Vector[matrix1RowsCount];

        for (int i = 0; i < matrix1RowsCount; i++) {
            double[] rowElements = new double[matrix2ColumnsCount];

            for (int j = 0; j < matrix2ColumnsCount; j++) {
                double sum = 0;

                for (int k = 0; k < matrix1ColumnsCount; k++) {
                    sum += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }

                rowElements[j] = sum;
            }

            resultRows[i] = new Vector(rowElements);
        }

        return new Matrix(resultRows);
    }
}
