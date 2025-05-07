package ru.academits.eliseev.models;

public class Model {
    public double toCelsius(double temp, String unit) {
        return switch (unit) {
            case "Цельсий" -> temp;
            case "Фаренгейт" -> (temp - 32) * 5 / 9;
            case "Кельвин" -> temp - 273.15;
            default -> throw new IllegalArgumentException("Неизвестная единица измерения: " + unit);
        };
    }

    public double fromCelsius(double celsius, String unit) {
        return switch (unit) {
            case "Цельсий" -> celsius;
            case "Фаренгейт" -> celsius * 9 / 5 + 32;
            case "Кельвин" -> celsius + 273.15;
            default -> throw new IllegalArgumentException("Неизвестная единица измерения: " + unit);
        };
    }
}
