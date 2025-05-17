package ru.academits.eliseev.temperature.view;

import javax.swing.*;

public interface TemperatureConverterView {
    JButton getConvertButton();
    void showIncorrectInputMessage();
    void showErrorMessage(String message);
    double getInputTemperature() throws NumberFormatException;
    String getInputScale();
    String getOutputScale();
    void setOutputTemperature(double temperature);
}
