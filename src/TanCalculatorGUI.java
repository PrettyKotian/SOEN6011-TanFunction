import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TanCalculatorGUI extends JFrame {

    private JTextField inputField;
    private JComboBox<String> unitBox;
    private JLabel resultLabel;

    public TanCalculatorGUI() {
        setTitle("tan(x) Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("tan(x) Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);
        unitBox = new JComboBox<>(new String[]{"Radians", "Degrees"});
        inputPanel.add(new JLabel("Enter x: "));
        inputPanel.add(inputField);
        inputPanel.add(unitBox);
        add(inputPanel);

        JButton computeButton = new JButton("Compute");
        add(computeButton);

        resultLabel = new JLabel("Result will be displayed here", SwingConstants.CENTER);
        add(resultLabel);

        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        JPanel controlPanel = new JPanel();
        controlPanel.add(resetButton);
        controlPanel.add(exitButton);
        add(controlPanel);

        computeButton.addActionListener(e -> calculateTan());
        resetButton.addActionListener(e -> resetFields());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void calculateTan() {
        try {
            String inputText = inputField.getText();
            if (inputText.trim().isEmpty()) {
                showError("Input field is empty.");
                return;
            }

            double x = Double.parseDouble(inputText);

            if (unitBox.getSelectedItem().equals("Degrees")) {
                x = Math.toRadians(x);
            }

            if (isUndefined(x)) {
                resultLabel.setText("tan(x) is undefined at this input.");
                return;
            }

            double sinX = computeSin(x, 10);
            double cosX = computeCos(x, 10);
            double tanX = sinX / cosX;

            resultLabel.setText(String.format("tan(%.4f) = %.6f", x, tanX));

        } catch (NumberFormatException ex) {
            showError("Invalid input. Please enter a real number.");
        }
    }

    private void resetFields() {
        inputField.setText("");
        resultLabel.setText("Result will be displayed here");
        unitBox.setSelectedIndex(0);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isUndefined(double x) {
        // tan(x) is undefined at odd multiples of PI/2
        double piOver2 = Math.PI / 2;
        double ratio = x / piOver2;
        return Math.abs(ratio - Math.round(ratio)) < 1e-6 && Math.round(ratio) % 2 != 0;
    }

    private double computeSin(double x, int terms) {
        double result = 0;
        for (int n = 0; n < terms; n++) {
            result += Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / factorial(2 * n + 1);
        }
        return result;
    }

    private double computeCos(double x, int terms) {
        double result = 0;
        for (int n = 0; n < terms; n++) {
            result += Math.pow(-1, n) * Math.pow(x, 2 * n) / factorial(2 * n);
        }
        return result;
    }

    private double factorial(int n) {
        double result = 1.0;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TanCalculatorGUI::new);
    }
}
