package ru.academits.eliseev.controllers;

import ru.academits.eliseev.models.Model;
import ru.academits.eliseev.views.View;

import java.util.Objects;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @SuppressWarnings("DataFlowIssue")
    public void initController() {
        view.getConvertButton().addActionListener(_ -> {
            try {
                double inputTemperature = Double.parseDouble(view.getTemperatureInputField().getText());
                String inputUnit = (String) view.getInputUnitComboBox().getSelectedItem();
                String outputUnit = (String) view.getOutputUnitComboBox().getSelectedItem();

                double resultTemperature = inputTemperature;

                if (!Objects.equals(inputUnit, outputUnit)) {
                    double celsius = model.toCelsius(inputTemperature, inputUnit);
                    resultTemperature = model.fromCelsius(celsius, outputUnit);
                }

                view.getTemperatureOutputField().setText(String.format("%.2f", resultTemperature));
            } catch (NumberFormatException e) {
                view.showIncorrectInputMessage();
            } catch (IllegalArgumentException e) {
                view.showErrorMessage(e.getMessage());
            }
        });
    }
}
