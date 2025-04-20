package ru.academits.eliseev.matrix;

import ru.academits.eliseev.vector.Vector;

import static ru.academits.eliseev.vector.Vector.getScalarProduct;

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

        int matrixRowsCount = matrix.getRowsCount();

        rows = new Vector[matrixRowsCount];

        for (int i = 0; i < matrixRowsCount; i++) {
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

        if (array.length == 0) {
            throw new IllegalArgumentException("Массив данных не может быть пустым.");
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Массив данных не может содержать пустые строки.");
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

            int vectorSize = vector.getSize();

            columnsCount = Math.max(vectorSize, columnsCount);
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            int currentVectorSize = vectors[i].getSize();

            if (currentVectorSize >= columnsCount) {
                rows[i] = new Vector(vectors[i]);
            } else {
                rows[i] = new Vector(columnsCount);

                for (int j = 0; j < currentVectorSize; j++) {
                    rows[i].setComponent(j, vectors[i].getComponent(j));
                }
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        int rowsCount = getRowsCount();

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: index = " + index + "; Допустимый диапазон: от 0 до " + (rowsCount - 1) + "(включительно)");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вставляемый вектор не может быть null.");
        }

        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        int vectorSize = vector.getSize();

        if (index < 0 || index >= rowsCount) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: index = " + index + "; Допустимый диапазон: от 0 до " + (rowsCount - 1) + "(включительно)");
        }

        if (vectorSize != columnsCount) {
            throw new IllegalArgumentException("Вставляемый вектор должен иметь столько же компонентов, сколько столбцов в матрице. Количество компонентов в данном векторе: " + vectorSize + "; Количество столбцов в матрице: " + columnsCount);
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

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
        int newRowsCount = getColumnsCount();
        Vector[] newRows = new Vector[newRowsCount];

        for (int i = 0; i < newRowsCount; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiply(double number) {
        for (Vector row : rows) {
            row.multiply(number);
        }
    }

    private static double[][] getSubmatrix(double[][] matrix, int column) {
        int size = matrix.length;
        double[][] submatrix = new double[size - 1][size - 1];

        for (int matrixRow = 1, submatrixRow = 0; matrixRow < size; matrixRow++) {
            for (int matrixColumn = 0, submatrixColumn = 0; matrixColumn < size; matrixColumn++) {
                if (matrixColumn == column) {
                    continue;
                }

                submatrix[submatrixRow][submatrixColumn] = matrix[matrixRow][matrixColumn];

                submatrixColumn++;
            }

            submatrixRow++;
        }

        return submatrix;
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
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

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
            joiner.add(row.toString());
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

        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

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

        assertMatrixSizesMatch(this, matrix);

        int thisMatrixRowsCount = getRowsCount();

        for (int i = 0; i < thisMatrixRowsCount; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Вычитаемая матрица не может быть null.");
        }

        assertMatrixSizesMatch(this, matrix);

        int thisMatrixRowsCount = getRowsCount();

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

        assertMatrixSizesMatch(matrix1, matrix2);

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

        assertMatrixSizesMatch(matrix1, matrix2);

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

        int matrix1RowsCount = matrix1.getRowsCount();
        int matrix1ColumnsCount = matrix1.getColumnsCount();
        int matrix2RowsCount = matrix2.getRowsCount();
        int matrix2ColumnsCount = matrix2.getColumnsCount();

        if (matrix1ColumnsCount != matrix2RowsCount) {
            throw new IllegalArgumentException("Число столбцов первой матрицы (" + matrix1ColumnsCount + ") должно равняться числу строк второй матрицы (" + matrix2RowsCount + ").");
        }

        Vector[] resultRows = new Vector[matrix1RowsCount];

        for (int i = 0; i < matrix1RowsCount; i++) {
            double[] rowElements = new double[matrix2ColumnsCount];

            Vector row = matrix1.getRow(i);

            for (int j = 0; j < matrix2ColumnsCount; j++) {
                Vector column = matrix2.getColumn(j);

                rowElements[j] = getScalarProduct(row, column);
            }

            resultRows[i] = new Vector(rowElements);
        }

        return new Matrix(resultRows);
    }

    private static void assertMatrixSizesMatch(Matrix matrix1, Matrix matrix2) {
        int matrix1RowsCount = matrix1.getRowsCount();
        int matrix1ColumnsCount = matrix1.getColumnsCount();
        int matrix2RowsCount = matrix2.getRowsCount();
        int matrix2ColumnsCount = matrix2.getColumnsCount();

        if (matrix1RowsCount != matrix2RowsCount || matrix1ColumnsCount != matrix2ColumnsCount) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров. Размеры первой матрицы: " + matrix1RowsCount + "x" + matrix1ColumnsCount + "; Размеры второй матрицы: " + matrix2RowsCount + "x" + matrix2ColumnsCount);
        }
    }
}
