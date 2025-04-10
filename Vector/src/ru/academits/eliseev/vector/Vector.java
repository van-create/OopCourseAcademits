package ru.academits.eliseev.vector;

import java.util.Arrays;
import java.util.StringJoiner;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть положительным числом. Текущее значение: " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Копируемый вектор не может быть null.");
        }

        components = vector.components.clone();
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив значений не может быть null.");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным числом. Текущее значение: " + array.length);
        }

        components = array.clone();
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть положительным числом. Текущее значение: " + size);
        }

        if (array == null) {
            throw new NullPointerException("Массив значений не может быть null.");
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");

        for (double component : components) {
            joiner.add(String.valueOf(component));
        }

        return joiner.toString();
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Прибавляемый вектор не может быть null.");
        }

        int vectorSize = vector.components.length;

        if (vectorSize > components.length) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вычитаемый вектор не может быть null.");
        }

        int vectorSize = vector.components.length;

        if (vectorSize > components.length) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void negate() {
        multiply(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double component : components) {
            squaresSum += component * component;
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Вектор 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Вектор 2 не может быть null.");
        }

        Vector resultVector = new Vector(vector1);

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Вектор 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Вектор 2 не может быть null.");
        }

        Vector resultVector = new Vector(vector1);

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Вектор 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Вектор 2 не может быть null.");
        }

        int minSize = Math.min(vector1.components.length, vector2.components.length);

        double scalarProduct = 0;

        for (int i = 0; i < minSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
