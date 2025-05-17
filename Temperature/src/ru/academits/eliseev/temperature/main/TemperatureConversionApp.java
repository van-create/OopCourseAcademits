package ru.academits.eliseev.temperature.main;

import ru.academits.eliseev.temperature.controller.Controller;
import ru.academits.eliseev.temperature.view.View;

public class TemperatureConversionApp {
    public static void main(String[] args) {
        View view = new View("Конвертер температур");
        Controller controller = new Controller(view);
        controller.initController();
    }
}
