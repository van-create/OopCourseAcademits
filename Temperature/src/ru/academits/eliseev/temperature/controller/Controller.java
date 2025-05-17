package ru.academits.eliseev.temperature.controller;

import ru.academits.eliseev.temperature.model.Model;
import ru.academits.eliseev.temperature.view.TemperatureConverterView;

import java.util.Objects;

public class Controller {
    private final TemperatureConverterView view;

    public Controller(TemperatureConverterView view) {
        this.view = view;
    }

    public void initController() {
        view.getConvertButton().addActionListener(_ -> {
            try {
                double inputTemperature = view.getInputTemperature();
                String inputScale = view.getInputScale();
                String outputScale = view.getOutputScale();

                double resultTemperature = inputTemperature;

                if (!Objects.equals(inputScale, outputScale)) {
                    double celsius = Model.convertToCelsius(inputTemperature, inputScale);
                    resultTemperature = Model.convertFromCelsius(celsius, outputScale);
                }

                view.setOutputTemperature(resultTemperature);
            } catch (NumberFormatException e) {
                view.showIncorrectInputMessage();
            } catch (IllegalArgumentException e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }
}