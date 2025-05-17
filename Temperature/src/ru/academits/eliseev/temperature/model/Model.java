package ru.academits.eliseev.temperature.model;

public class Model {
    public static double convertToCelsius(double temp, String scale) {
        return switch (scale) {
            case "Цельсий" -> temp;
            case "Фаренгейт" -> (temp - 32) * 5 / 9;
            case "Кельвин" -> temp - 273.15;
            default -> throw new IllegalArgumentException("Неизвестная единица измерения: " + scale);
        };
    }

    public static double convertFromCelsius(double celsius, String scale) {
        return switch (scale) {
            case "Цельсий" -> celsius;
            case "Фаренгейт" -> celsius * 9 / 5 + 32;
            case "Кельвин" -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Неизвестная единица измерения: " + scale);
        };
    }
}
