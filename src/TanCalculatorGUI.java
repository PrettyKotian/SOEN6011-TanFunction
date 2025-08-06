package TanFunctionCalculator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        final JPanel controlPanel = new JPanel();
        final JButton computeButton = new JButton("Compute");
        final JButton resetButton = new JButton("Reset");
        final JButton exitButton = new JButton("Exit");

        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeTan();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultLabel.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        controlPanel.add(computeButton);
        controlPanel.add(resetButton);
        controlPanel.add(exitButton);
        add(controlPanel);

        final JPanel resultPanel = new JPanel();
        resultLabel = new JLabel("");
        resultPanel.add(resultLabel);
        add(resultPanel);
    }

    private void computeTan() {
        try {
            double input = Double.parseDouble(inputField.getText());
            String unit = (String) unitBox.getSelectedItem();
            if ("Degrees".equals(unit)) {
                input = Math.toRadians(input);
            }
            double result = Math.tan(input);
            resultLabel.setText("tan(x) = " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input.");
        }
    }

    public static void main(String[] args) {
        TanCalculatorGUI calculator = new TanCalculatorGUI();
        calculator.setVisible(true);
    }
}
