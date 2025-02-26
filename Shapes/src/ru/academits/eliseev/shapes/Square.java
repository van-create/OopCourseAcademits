package ru.academits.eliseev.shapes;

public class Square implements Shapes {
    private final double sideLength;
    public static final int SIDES_COUNT = 4;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * SIDES_COUNT;
    }

    @Override
    public String toString() {
        return "Фигура: Квадрат\nШирина: " + this.getWidth() + "\nДлина: " + this.getHeight() + "\nПлощадь: " + this.getArea() + "\nПериметр: " + this.getPerimeter();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Square square = (Square) object;

        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
