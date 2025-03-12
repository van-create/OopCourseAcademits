package ru.academits.eliseev.vector;

import java.util.Arrays;

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
            throw new NullPointerException("Копируемый объект не может быть null.");
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

        if (array.length == 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным числом. Текущее значение: " + array.length);
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(components).replace("[", "{").replace("]", "}");
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Прибавляемый объект не может быть null.");
        }

        if (vector.components.length > components.length) {
            double[] resultComponents = new double[vector.components.length];

            for (int i = 0; i < vector.components.length; i++) {
                double val1 = (i < components.length) ? components[i] : 0;

                resultComponents[i] = val1 + vector.components[i];
            }

            components = resultComponents;
        } else {
            for (int i = 0; i < components.length; i++) {
                double val2 = (i < vector.components.length) ? vector.components[i] : 0;

                components[i] += val2;
            }
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вычитаемый объект не может быть null.");
        }

        if (vector.components.length > components.length) {
            double[] resultComponents = new double[vector.components.length];

            for (int i = 0; i < vector.components.length; i++) {
                double val1 = (i < components.length) ? components[i] : 0;

                resultComponents[i] = val1 - vector.components[i];
            }

            components = resultComponents;
        } else {
            for (int i = 0; i < components.length; i++) {
                double val2 = (i < vector.components.length) ? vector.components[i] : 0;

                components[i] -= val2;
            }
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

        for (double element : components) {
            squaresSum += element * element;
        }

        return Math.sqrt(squaresSum);
    }

    public double getElement(int index) {
        return components[index];
    }

    public void setElement(int index, double element) {
        components[index] = element;
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
            throw new NullPointerException("Объект 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Объект 2 не может быть null.");
        }

        Vector resultVector = new Vector(vector1);

        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Объект 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Объект 2 не может быть null.");
        }

        Vector resultVector = new Vector(vector1);

        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("Объект 1 не может быть null.");
        }

        if (vector2 == null) {
            throw new NullPointerException("Объект 2 не может быть null.");
        }

        double result = 0;
        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
