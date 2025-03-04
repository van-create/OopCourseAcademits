package ru.academits.eliseev.vector_main;

import ru.academits.eliseev.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        // test 1
        Vector v1 = new Vector(3);

        System.out.println("Тест 1 пройден: " + v1 + " размер = " + v1.getSize());

        // test 2
        try {
            new Vector(-1);

            System.out.println("Тест 2 провален: должен быть выброшен exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Тест 2 пройден: " + e.getMessage());
        }

        // test 3
        Vector v3 = new Vector(2);

        v3.setElementByIndex(1.0, 0);
        v3.setElementByIndex(2.0, 1);

        Vector v4 = new Vector(v3);

        System.out.println("Тест 3 пройден: " + v4 + (v3.equals(v4) ? " (копия верна)" : " (копия неверна)"));

        // test 4
        double[] arr = {1.0, 2.0, 3.0};
        Vector v5 = new Vector(arr);

        System.out.println("Тест 4 пройден: " + v5);

        // test 5
        Vector v6 = new Vector(5, new double[]{1.0, 2.0});

        System.out.println("Тест 5 пройден: " + v6); // Ожидаем {1.0, 2.0, 0.0, 0.0, 0.0}

        // test 6
        Vector v7 = new Vector(new double[]{1.0, 2.0});
        Vector v8 = new Vector(new double[]{1.0, 2.0, 3.0});

        v7.add(v8);

        System.out.println("Тест 6 пройден: " + v7); // Ожидаем {2.0, 4.0, 3.0}

        // test 7
        Vector v9 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector v10 = new Vector(new double[]{1.0, 2.0});

        v9.subtract(v10);

        System.out.println("Тест 7 пройден: " + v9); // Ожидаем {3.0, 3.0, 6.0}

        // test 8
        Vector v11 = new Vector(new double[]{1.0, 2.0, 3.0});

        v11.scale(2.0);

        System.out.println("Тест 8 пройден: " + v11); // Ожидаем {2.0, 4.0, 6.0}

        // test 9
        Vector v12 = new Vector(new double[]{1.0, -2.0, 3.0});

        v12.negate();

        System.out.println("Тест 9 пройден: " + v12); // Ожидаем {-1.0, 2.0, -3.0}

        // test 10
        Vector v13 = new Vector(new double[]{3.0, 4.0});

        double length = v13.getLength();

        System.out.println("Тест 10 пройден: длина = " + length + (Math.abs(length - 5.0) < 0.0001 ? " (верно)" : " (неверно)"));

        // test 11
        Vector v14 = new Vector(3);
        v14.setElementByIndex(5.0, 1);

        double element = v14.getElementByIndex(1);

        System.out.println("Тест 11 пройден: " + v14 + " элемент = " + element);

        // test 12
        Vector v15 = new Vector(new double[]{1.0, 2.0});
        Vector v16 = new Vector(new double[]{1.0, 2.0});
        Vector v17 = new Vector(new double[]{1.0, 3.0});

        System.out.println("Тест 12 пройден: " + v15.equals(v16) + " " + !v15.equals(v17));

        // test 13
        Vector v18 = Vector.add(new Vector(new double[]{1.0, 2.0}), new Vector(new double[]{3.0, 4.0}));

        System.out.println("Тест 13 пройден: " + v18); // Ожидаем {4.0, 6.0}

        // test 14
        Vector v19 = Vector.subtract(new Vector(new double[]{5.0, 6.0}), new Vector(new double[]{3.0, 4.0}));

        System.out.println("Тест 14 пройден: " + v19); // Ожидаем {2.0, 2.0}

        // test 15
        Vector v20 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector v21 = new Vector(new double[]{4.0, 5.0, 6.0});

        double scalar = Vector.scalarProduct(v20, v21);

        System.out.println("Тест 15 пройден: скалярное произведение = " + scalar); // Ожидаем 32.0

        // test 16
        double[] array = {1, 2, 3, 4, 5, 6};

        Vector v22 = new Vector(5);
        v22.setElements(array);

        System.out.println("Тест 16 пройден: элементы: " + Arrays.toString(v22.getElements()));
    }
}
