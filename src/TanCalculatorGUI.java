package TanCalculator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

public class TanCalculatorGUI extends JFrame {

    private JTextField inputField;
    private JComboBox<String> unitBox;
    private JLabel resultLabel;

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

        final JPanel resetPanel = new JPanel();
        final JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText("");
        });
        resetPanel.add(resetButton);
        add(resetPanel);

        final JPanel exitPanel = new JPanel();
        final JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        exitPanel.add(exitButton);
        add(exitPanel);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        add(resultLabel);
    }

    private void computeTan() {
        try {
            double input = Double.parseDouble(inputField.getText());
            final String unit = (String) unitBox.getSelectedItem();
            if ("Degrees".equals(unit)) {
                input = Math.toRadians(input);
            }

            final double result = Math.tan(input);
            resultLabel.setText("tan(x) = " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input.");
        }
    }

    public static void main(String[] args) {
        final TanCalculatorGUI calculator = new TanCalculatorGUI();
        calculator.setVisible(true);
    }
}
