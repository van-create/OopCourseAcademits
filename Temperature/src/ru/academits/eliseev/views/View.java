package ru.academits.eliseev.views;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class View {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JLabel inputUnitLabel;
    private JLabel outputUnitLabel;
    private JTextField temperatureInputField;
    private JTextField temperatureOutputField;
    private JComboBox<String> inputUnitComboBox;
    private JComboBox<String> outputUnitComboBox;
    private JButton convertButton;

    public View(String title) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 2 - frame.getWidth() / 2, screenHeight / 2 - frame.getHeight() / 2);
        frame.setMinimumSize(new Dimension(screenWidth / 3, screenHeight / 3));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleLabel = new JLabel("Конвертер температур", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        inputLabel = new JLabel("Введите температуру:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(inputLabel, gbc);

        temperatureInputField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(temperatureInputField, gbc);

        inputUnitLabel = new JLabel("Из единицы:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(inputUnitLabel, gbc);

        String[] units = {"Цельсий", "Фаренгейт", "Кельвин"};
        inputUnitComboBox = new JComboBox<>(units);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(inputUnitComboBox, gbc);

        outputUnitLabel = new JLabel("В единицу:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(outputUnitLabel, gbc);

        outputUnitComboBox = new JComboBox<>(units);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(outputUnitComboBox, gbc);

        outputLabel = new JLabel("Результат:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(outputLabel, gbc);

        temperatureOutputField = new JTextField(10);
        temperatureOutputField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(temperatureOutputField, gbc);

        convertButton = new JButton("Преобразовать");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(convertButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JLabel getInputLabel() {
        return inputLabel;
    }

    public void setInputLabel(JLabel inputLabel) {
        this.inputLabel = inputLabel;
    }

    public JLabel getOutputLabel() {
        return outputLabel;
    }

    public void setOutputLabel(JLabel outputLabel) {
        this.outputLabel = outputLabel;
    }

    public JLabel getInputUnitLabel() {
        return inputUnitLabel;
    }

    public void setInputUnitLabel(JLabel inputUnitLabel) {
        this.inputUnitLabel = inputUnitLabel;
    }

    public JLabel getOutputUnitLabel() {
        return outputUnitLabel;
    }

    public void setOutputUnitLabel(JLabel outputUnitLabel) {
        this.outputUnitLabel = outputUnitLabel;
    }

    public JTextField getTemperatureInputField() {
        return temperatureInputField;
    }

    public void setTemperatureInputField(JTextField temperatureInputField) {
        this.temperatureInputField = temperatureInputField;
    }

    public JTextField getTemperatureOutputField() {
        return temperatureOutputField;
    }

    public void setTemperatureOutputField(JTextField temperatureOutputField) {
        this.temperatureOutputField = temperatureOutputField;
    }

    public JComboBox<String> getInputUnitComboBox() {
        return inputUnitComboBox;
    }

    public void setInputUnitComboBox(JComboBox<String> inputUnitComboBox) {
        this.inputUnitComboBox = inputUnitComboBox;
    }

    public JComboBox<String> getOutputUnitComboBox() {
        return outputUnitComboBox;
    }

    public void setOutputUnitComboBox(JComboBox<String> outputUnitComboBox) {
        this.outputUnitComboBox = outputUnitComboBox;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public void setConvertButton(JButton convertButton) {
        this.convertButton = convertButton;
    }

    public void showIncorrectInputMessage() {
        JOptionPane.showMessageDialog(getTemperatureInputField(),
                "Пожалуйста, введите корректное число",
                "Ошибка ввода",
                JOptionPane.ERROR_MESSAGE);
        getTemperatureOutputField().setText("");
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(getTemperatureInputField(),
                message,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
        getTemperatureOutputField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View("Конвертер температур"));
    }
}
