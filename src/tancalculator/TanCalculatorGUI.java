package tancalculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * GUI application to compute tan(x).
 */
public class TanCalculatorGUI extends JFrame {

    private static final Logger logger = Logger.getLogger(TanCalculatorGUI.class.getName());

    private final JTextField inputField;
    private final JComboBox<String> unitBox;
    private final JLabel resultLabel;

    /**
     * Constructor to set up the GUI components.
     */
    public TanCalculatorGUI() {
        setTitle("tan(x) Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(6, 1)); // 6 rows needed

        final JLabel title = new JLabel("tan(x) Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        final JPanel inputPanel = new JPanel();
        final JLabel inputLabel = new JLabel("Enter value of x:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        inputField = new JTextField(10);
        unitBox = new JComboBox<>(new String[]{"Radians", "Degrees"});

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(unitBox);
        add(inputPanel);

        final JPanel computePanel = new JPanel();
        final JButton computeButton = new JButton("Compute tan(x)");
        computeButton.addActionListener(e -> computeTan());
        computePanel.add(computeButton);
        add(computePanel);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        add(resultLabel);

        final JPanel resetPanel = new JPanel();
        final JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText("");
            logger.log(Level.INFO, "Reset button clicked - input cleared.");
        });
        resetPanel.add(resetButton);
        add(resetPanel);

        final JPanel exitPanel = new JPanel();
        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            logger.log(Level.INFO, "Exit button clicked - application closing.");
            System.exit(0);
        });
        exitPanel.add(exitButton);
        add(exitPanel);
    }

    /**
     * Method to compute tan(x) based on input and unit.
     */
    private void computeTan() {
        try {
            final double originalInput = Double.parseDouble(inputField.getText());
            final String unit = (String) unitBox.getSelectedItem();
            final boolean isDegrees = "Degrees".equals(unit);

            logger.log(Level.INFO, () -> String.format("Input received: %.6f", originalInput));

            double result = calculateTan(originalInput, isDegrees);
            resultLabel.setText(String.format("tan(x) = %.6f", result));

            logger.log(Level.INFO, () -> String.format("Computed tan(x): %.6f", result));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input.");
            logger.log(Level.WARNING, "Invalid input entered.");
        }
    }

    /**
     * Static method to calculate tan(x) for testing.
     *
     * @param value     input x value
     * @param isDegrees true if the input is in degrees
     * @return computed tan(x)
     */
    public static double calculateTan(double value, boolean isDegrees) {
        if (isDegrees) {
            value = Math.toRadians(value);
        }
        return Math.tan(value);
    }

    /**
     * Main method to launch the calculator.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final TanCalculatorGUI calculator = new TanCalculatorGUI();
            calculator.setVisible(true);
            logger.log(Level.INFO, "Application started and GUI launched.");
        });
    }
}
