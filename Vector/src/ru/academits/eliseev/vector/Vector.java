package ru.academits.eliseev.vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть положительным числом.");
        }

        elements = new double[n];

        for (int i = 0; i < n; i++) {
            elements[i] = 0;
        }
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Копируемый объект не может быть null.");
        }

        elements = vector.elements.clone();
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив значений не может быть null.");
        }

        elements = array.clone();
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть положительным числом.");
        }

        if (array == null) {
            throw new NullPointerException("Массив значений не может быть null.");
        }

        this.elements = new double[n];

        for (int i = 0; i < array.length && i < n; i++) {
            this.elements[i] = array[i];
        }

        for (int i = array.length; i < n; i++) {
            this.elements[i] = 0;
        }
    }

    public double[] getElements() {
        return elements;
    }

    public void setElements(double[] array) {
        if (array == null) {
            throw new NullPointerException("Массив значений не может быть null.");
        }

        elements = array.clone();
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i < elements.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Прибавляемый объект не может быть null.");
        }

        int maxLength = Math.max(this.elements.length, vector.elements.length);
        double[] newComponents = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double val1 = (i < this.elements.length) ? this.elements[i] : 0;
            double val2 = (i < vector.elements.length) ? vector.elements[i] : 0;

            newComponents[i] = val1 + val2;
        }

        this.elements = newComponents;
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Прибавляемый объект не может быть null.");
        }

        int maxLength = Math.max(this.elements.length, vector.elements.length);
        double[] newComponents = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double val1 = (i < this.elements.length) ? this.elements[i] : 0;
            double val2 = (i < vector.elements.length) ? vector.elements[i] : 0;

            newComponents[i] = val1 - val2;
        }

        this.elements = newComponents;
    }

    public void scale(double number) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= number;
        }
    }

    public void negate() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= -1;
        }
    }

    public double getLength() {
        double sumOfSquares = 0;

        for (double element : elements) {
            sumOfSquares += element * element;
        }

        return Math.sqrt(sumOfSquares);
    }

    public double getElementByIndex(int index) {
        return elements[index];
    }

    public void setElementByIndex(double element, int index) {
        elements[index] = element;
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

        if (vector.elements.length != elements.length) {
            return false;
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != vector.elements[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(elements);

        return hash;
    }

    public static Vector add(Vector v1, Vector v2) {
        if (v1 == null || v2 == null) {
            throw new NullPointerException("Объекты не могут быть null.");
        }

        Vector newVector = new Vector(v1);

        newVector.add(v2);

        return newVector;
    }

    public static Vector subtract(Vector v1, Vector v2) {
        if (v1 == null || v2 == null) {
            throw new NullPointerException("Объекты не могут быть null.");
        }

        Vector newVector = new Vector(v1);

        newVector.subtract(v2);

        return newVector;
    }

    public static double scalarProduct(Vector v1, Vector v2) {
        double result = 0;

        for (int i = 0; i < Math.min(v1.elements.length, v2.elements.length); i++) {
            result += v1.elements[i] * v2.elements[i];
        }

        return result;
    }
}
