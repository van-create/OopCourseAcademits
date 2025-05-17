package ru.academits.eliseev.temperature.view;

import javax.swing.*;
import java.awt.*;

public class View implements TemperatureConverterView {
    private final JFrame frame;
    private final JLabel titleLabel;
    private final JLabel inputLabel;
    private final JLabel outputLabel;
    private final JLabel inputScaleLabel;
    private final JLabel outputScaleLabel;
    private final JTextField temperatureInputField;
    private final JTextField temperatureOutputField;
    private final JComboBox<String> inputScaleComboBox;
    private final JComboBox<String> outputScaleComboBox;
    private final JButton convertButton;

    public View(String title) {
        frame = new JFrame(title);
        titleLabel = new JLabel("Конвертер температур", SwingConstants.CENTER);
        inputLabel = new JLabel("Введите температуру:");
        outputLabel = new JLabel("Результат:");
        inputScaleLabel = new JLabel("Из единицы:");
        outputScaleLabel = new JLabel("В единицу:");
        temperatureInputField = new JTextField(10);
        temperatureOutputField = new JTextField(10);
        String[] scales = {"Цельсий", "Фаренгейт", "Кельвин"};
        inputScaleComboBox = new JComboBox<>(scales);
        outputScaleComboBox = new JComboBox<>(scales);
        convertButton = new JButton("Преобразовать");

        SwingUtilities.invokeLater(this::initializeUI);
    }

    private void initializeUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth / 3, screenHeight / 3);
        frame.setLocation(screenWidth / 2 - frame.getWidth() / 2, screenHeight / 2 - frame.getHeight() / 2);
        frame.setMinimumSize(new Dimension(screenWidth / 3, screenHeight / 3));
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(inputLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(temperatureInputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(inputScaleLabel, gbc);

        inputScaleComboBox.setSelectedItem("Цельсий");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(inputScaleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(outputScaleLabel, gbc);

        outputScaleComboBox.setSelectedItem("Фаренгейт");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(outputScaleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(outputLabel, gbc);

        temperatureOutputField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(temperatureOutputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(convertButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public JButton getConvertButton() {
        return convertButton;
    }

    @Override
    public void showIncorrectInputMessage() {
        JOptionPane.showMessageDialog(temperatureInputField,
                "Пожалуйста, введите корректное число",
                "Ошибка ввода",
                JOptionPane.ERROR_MESSAGE);
        temperatureOutputField.setText("");
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(temperatureInputField,
                message,
                "Ошибка",
                JOptionPane.ERROR_MESSAGE);
        temperatureOutputField.setText("");
    }

    @Override
    public double getInputTemperature() throws NumberFormatException {
        String inputText = temperatureInputField.getText().trim();
        if (inputText.isEmpty()) {
            throw new NumberFormatException("Пустой ввод");
        }
        return Double.parseDouble(inputText);
    }

    @Override
    public String getInputScale() {
        return (String) inputScaleComboBox.getSelectedItem();
    }

    @Override
    public String getOutputScale() {
        return (String) outputScaleComboBox.getSelectedItem();
    }

    @Override
    public void setOutputTemperature(double temperature) {
        temperatureOutputField.setText(String.format("%.2f", temperature));
    }
}