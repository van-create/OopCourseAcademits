package ru.academits.eliseev.matrix;

import ru.academits.eliseev.vector.Vector;

import java.util.StringJoiner;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть положительным числом: rows = " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть положительным числом: columns = " + columnsCount);
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

        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустой.");
        }

        int columns = array[0].length;

        for (double[] row : array) {
            if (row.length != columns) {
                throw new IllegalArgumentException("Все строки матрицы должны иметь одинаковую длину.");
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Массив векторов не может быть null.");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым.");
        }

        int columnCount = vectors[0].getSize();

        for (Vector vector : vectors) {
            if (vector == null) {
                throw new NullPointerException("Элемент массива векторов не может быть null.");
            }

            if (vector.getSize() != columnCount) {
                throw new IllegalArgumentException("Все векторы должны иметь одинаковую длину: ожидалось " + columnCount + ", найдено " + vector.getSize());
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(vectors[i]);
        }
    }

    public String getDimensions() {
        return "Размеры матрицы: " + rows.length + 'x' + rows[0].getSize();
    }

    public void setRowVector(int index, Vector vector) {
        if (index < 0 || index > rows.length) {
            throw new IllegalArgumentException("Индекс вне диапазона.");
        }

        if (vector == null) {
            throw new NullPointerException("Вставляемый вектор не может быть null.");
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Вставляемый вектор должен иметь столько же компонентов, сколько столбцов в матрице. Количество компонентов в данном векторе: " + vector.getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getRowVector(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Индекс вне диапазона.");
        }

        return new Vector(rows[index]);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IllegalArgumentException("Индекс вне диапазона.");
        }

        double[] tempArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            tempArray[i] = rows[i].getComponent(index);
        }

        return new Vector(tempArray);
    }

    public void transpose() {
        int rowsCount = rows[0].getSize();
        int columnsCount = rows.length;

        double[][] tempArray = new double[rowsCount][columnsCount];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                tempArray[i][j] = rows[j].getComponent(i);
            }
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(tempArray[i]);
        }
    }

    public void multiply(int number) {
        for (Vector row : rows) {
            row.multiply(number);
        }
    }

    private double[][] getMinor(double[][] matrix, int column) {
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

    private double calculateDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }

        double determinant = 0;

        for (int k = 0; k < matrix.length; k++) {
            determinant += Math.pow(-1, k) * matrix[0][k] * calculateDeterminant(getMinor(matrix, k));
        }

        return determinant;
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной.");
        }

        int size = rows.length;

        double[][] matrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rows[i].getComponent(j);
            }
        }

        return calculateDeterminant(matrix);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (Vector row : rows) {
            joiner.add(String.valueOf(row));
        }

        return joiner.toString();
    }

    public void multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор не может быть null.");
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException("Вектор должен иметь столько компонентов, сколько столбцов в матрице.");
        }

        Vector[] result = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            double currentComponent = 0;

            for (int j = 0; j < vector.getSize(); j++) {
                currentComponent += rows[i].getComponent(j) * vector.getComponent(j);
            }

            result[i] = new Vector(new double[]{currentComponent});
        }

        rows = result;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Прибавляемая матрица не может быть null.");
        }

        if (!this.getDimensions().equals(matrix.getDimensions())) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Вычитаемая матрица не может быть null.");
        }

        if (!this.getDimensions().equals(matrix.getDimensions())) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < rows.length; i++) {
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

        if (!matrix1.getDimensions().equals(matrix2.getDimensions())) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров.");
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

        if (!matrix1.getDimensions().equals(matrix2.getDimensions())) {
            throw new IllegalArgumentException("Матрицы должны быть одинаковых размеров.");
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
