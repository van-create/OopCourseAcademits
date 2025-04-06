package ru.academits.eliseev.shapes_comparators;

import ru.academits.eliseev.shapes.*;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.getPerimeter(), s2.getPerimeter());
    }
}

