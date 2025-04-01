package ru.academits.eliseev.vector_main;

import ru.academits.eliseev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Vector(3));

        runTests();
    }

    public static void runTests() {
        // test 1
        Vector vector1 = new Vector(3);

        System.out.println("Тест 1 пройден: " + vector1 + " размер = " + vector1.getSize());

        // test 2
        try {
            new Vector(-1);

            System.out.println("Тест 2 провален: должен быть выброшен exception");
        } catch (IllegalArgumentException exception) {
            System.out.println("Тест 2 пройден: " + exception.getMessage());
        }

        // test 3
        Vector vector3 = new Vector(2);

        vector3.setComponent(0, 1);
        vector3.setComponent(1, 2);

        Vector vector4 = new Vector(vector3);

        System.out.println("Тест 3 пройден: " + vector4 + (vector3.equals(vector4) ? " (копия верна)" : " (копия неверна)"));

        // test 4
        double[] array = {1.0, 2.0, 3.0};
        Vector vector5 = new Vector(array);

        System.out.println("Тест 4 пройден: " + vector5);

        // test 5
        Vector vector6 = new Vector(5, new double[]{1.0, 2.0});

        System.out.println("Тест 5 пройден: " + vector6); // Ожидаем {1.0, 2.0, 0.0, 0.0, 0.0}

        // test 6
        Vector vector7 = new Vector(new double[]{1.0, 2.0});
        Vector vector8 = new Vector(new double[]{1.0, 2.0, 3.0});

        vector7.add(vector8);

        System.out.println("Тест 6 пройден: " + vector7); // Ожидаем {2.0, 4.0, 3.0}

        // test 7
        Vector vector9 = new Vector(new double[]{4.0, 5.0, 6.0});
        Vector vector10 = new Vector(new double[]{1.0, 2.0});

        vector9.subtract(vector10);

        System.out.println("Тест 7 пройден: " + vector9); // Ожидаем {3.0, 3.0, 6.0}

        // test 8
        Vector vector11 = new Vector(new double[]{1.0, 2.0, 3.0});

        vector11.multiply(2.0);

        System.out.println("Тест 8 пройден: " + vector11); // Ожидаем {2.0, 4.0, 6.0}

        // test 9
        Vector vector12 = new Vector(new double[]{1.0, -2.0, 3.0});

        vector12.negate();

        System.out.println("Тест 9 пройден: " + vector12); // Ожидаем {-1.0, 2.0, -3.0}

        // test 10
        Vector vector13 = new Vector(new double[]{3.0, 4.0});

        double length = vector13.getLength();
        System.out.println("Тест 10 пройден: длина = " + length + (Math.abs(length - 5.0) < 0.0001 ? " (верно)" : " (неверно)"));

        // test 11
        Vector vector14 = new Vector(3);
        vector14.setComponent(1, 5.0);

        double component = vector14.getComponent(1);
        System.out.println("Тест 11 пройден: " + vector14 + " элемент = " + component);

        // test 12
        Vector vector15 = new Vector(new double[]{1.0, 2.0});
        Vector vector16 = new Vector(new double[]{1.0, 2.0});
        Vector vector17 = new Vector(new double[]{1.0, 3.0});

        System.out.println("Тест 12 пройден: " + vector15.equals(vector16) + " " + !vector15.equals(vector17));

        // test 13
        Vector vector18 = Vector.getSum(new Vector(new double[]{1.0, 2.0}), new Vector(new double[]{3.0, 4.0}));

        System.out.println("Тест 13 пройден: " + vector18); // Ожидаем {4.0, 6.0}

        // test 14
        Vector vector19 = Vector.getDifference(new Vector(new double[]{5.0, 6.0}), new Vector(new double[]{3.0, 4.0}));

        System.out.println("Тест 14 пройден: " + vector19); // Ожидаем {2.0, 2.0}

        // test 15
        Vector vector20 = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector vector21 = new Vector(new double[]{4.0, 5.0, 6.0});

        System.out.println("Тест 15 пройден: скалярное произведение = " + Vector.getScalarProduct(vector20, vector21)); // Ожидаем 32.0
    }
}
