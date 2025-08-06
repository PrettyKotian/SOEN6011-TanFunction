package tancalculator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TanCalculatorGUI extends JFrame {

    private static final Logger logger = Logger.getLogger(TanCalculatorGUI.class.getName());

    private final JTextField inputField;
    private final JComboBox<String> unitBox;
    private final JLabel resultLabel;

    public TanCalculatorGUI() {
        setTitle("tan(x) Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(6, 1));

        final JLabel title = new JLabel("tan(x) Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        final JPanel inputPanel = new JPanel();
        inputField = new JTextField(10);
        unitBox = new JComboBox<>(new String[]{"Radians", "Degrees"});
        inputPanel.add(inputField);
        inputPanel.add(unitBox);
        add(inputPanel);

        final JPanel computePanel = new JPanel();
        final JButton computeButton = new JButton("Compute tan(x)");
        computeButton.addActionListener(e -> computeTan());
        computePanel.add(computeButton);
        add(computePanel);

        // Initialize resultLabel early to satisfy final field rule
        resultLabel = new JLabel("", SwingConstants.CENTER);
        add(resultLabel);

        final JPanel resetPanel = new JPanel();
        final JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText("");
            if (logger.isLoggable(Level.INFO)) {
                logger.log(Level.INFO, () -> "Reset button clicked - input cleared.");
            }
        });
        resetPanel.add(resetButton);
        add(resetPanel);

        final JPanel exitPanel = new JPanel();
        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            if (logger.isLoggable(Level.INFO)) {
                logger.log(Level.INFO, () -> "Exit button clicked - application closing.");
            }
            System.exit(0);
        });
        exitPanel.add(exitButton);
        add(exitPanel);
    }

    private void computeTan() {
        try {
            double input = Double.parseDouble(inputField.getText());
            final String unit = (String) unitBox.getSelectedItem();

            if (logger.isLoggable(Level.INFO)) {
                final double finalInput = input;
                logger.log(Level.INFO, () -> String.format("Input received: %f", finalInput));
            }

            if ("Degrees".equals(unit)) {
                input = Math.toRadians(input);
            }

            final double result = Math.tan(input);
            resultLabel.setText("tan(x) = " + result);

            if (logger.isLoggable(Level.INFO)) {
                logger.log(Level.INFO, () -> String.format("Computed tan(x): %f", result));
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input.");
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, () -> "Invalid input entered.");
            }
        }
    }

    public static void main(String[] args) {
        final TanCalculatorGUI calculator = new TanCalculatorGUI();
        calculator.setVisible(true);

        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, () -> "Application started and GUI launched.");
        }
    }
}
