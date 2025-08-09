package tancalculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * GUI application to compute tan(x).
 * <p>Follows basic UI design principles: clear hierarchy, spacing, feedback,
 * keyboard focus, a11y names/descriptions, and consistent styles.</p>
 */
public final class TanCalculatorGUI extends JFrame {

    // ========= Styling & a11y constants =========
    private static final String APP_TITLE = "tan(x) Calculator";
    private static final String FONT_FAMILY = "Arial";
    private static final int TITLE_SIZE = 22;
    private static final int LABEL_SIZE = 14;
    private static final int BTN_SIZE = 14;
    private static final int PAD = 8;

    private static final String ACC_INPUT_NAME = "Input value for x";
    private static final String ACC_INPUT_DESC = "Enter a real number for x.";
    private static final String ACC_UNITS_NAME = "Units selection";
    private static final String ACC_UNITS_DESC =
            "Choose whether x is radians or degrees.";
    private static final String ACC_COMPUTE_NAME = "Compute tangent";
    private static final String ACC_RESET_NAME = "Reset form";
    private static final String ACC_RESET_DESC = "Clear the input and result.";
    private static final String ACC_EXIT_NAME = "Exit application";
    private static final String ACC_RESULT_NAME = "Computation result";
    private static final String ACC_RESULT_DESC =
            "Displays the value of tan(x), or 'undefined' when not defined.";

    // ========= Logging =========
    private static final Logger LOGGER =
            Logger.getLogger(TanCalculatorGUI.class.getName());

    // ========= UI state =========
    private final JTextField inputField;
    private final JComboBox<String> unitBox;
    private final JLabel resultLabel;
    private final JLabel statusLabel;

    /**
     * Constructor to set up the GUI components.
     */
    public TanCalculatorGUI() {
        super(APP_TITLE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(520, 360));
        setLocationByPlatform(true);

        // Root layout + padding panel
        final JPanel root = new JPanel(new BorderLayout(PAD, PAD));
        root.setBorder(javax.swing.BorderFactory.createEmptyBorder(PAD, PAD, PAD, PAD));
        setContentPane(root);

        // ===== Header =====
        final JLabel title = new JLabel(APP_TITLE, SwingConstants.CENTER);
        title.setFont(new Font(FONT_FAMILY, Font.BOLD, TITLE_SIZE));
        title.getAccessibleContext().setAccessibleName(APP_TITLE);
        title.getAccessibleContext().setAccessibleDescription("Application title.");
        root.add(title, BorderLayout.NORTH);

        // ===== Center form =====
        final JPanel center = new JPanel(new GridBagLayout());
        root.add(center, BorderLayout.CENTER);

        final GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(PAD, PAD, PAD, PAD);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;

        // Row 0: label + input + unit selector
        final JLabel inputLabel = new JLabel("x:");
        inputLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, LABEL_SIZE));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.0;
        center.add(inputLabel, gc);

        inputField = new JTextField(14);
        inputField.setFont(new Font(FONT_FAMILY, Font.PLAIN, LABEL_SIZE));
        inputField.setToolTipText("Enter x (real number)");
        inputField.getAccessibleContext().setAccessibleName(ACC_INPUT_NAME);
        inputField.getAccessibleContext().setAccessibleDescription(ACC_INPUT_DESC);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.weightx = 1.0;
        center.add(inputField, gc);

        unitBox = new JComboBox<>(new String[]{"Radians", "Degrees"});
        unitBox.setFont(new Font(FONT_FAMILY, Font.PLAIN, LABEL_SIZE));
        unitBox.setToolTipText("Interpret x as radians or degrees.");
        unitBox.getAccessibleContext().setAccessibleName(ACC_UNITS_NAME);
        unitBox.getAccessibleContext().setAccessibleDescription(ACC_UNITS_DESC);
        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 0.0;
        center.add(unitBox, gc);

        // Row 1: status (non-intrusive, under the input)
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, LABEL_SIZE));
        statusLabel.setForeground(new java.awt.Color(90, 90, 90));
        statusLabel.setToolTipText("Hints and validation feedback.");
        statusLabel.getAccessibleContext().setAccessibleName("Status");
        statusLabel.getAccessibleContext().setAccessibleDescription(
                "Shows short feedback about input or operations.");
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 3;
        gc.weightx = 1.0;
        center.add(statusLabel, gc);
        gc.gridwidth = 1; // reset

        // Row 2: compute button
        final JButton computeButton = new JButton("Compute tan(x)");
        computeButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, BTN_SIZE));
        computeButton.setToolTipText("Calculate tan(x) for the provided value and units.");
        computeButton.getAccessibleContext().setAccessibleName(ACC_COMPUTE_NAME);
        computeButton.addActionListener(this::onCompute);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 3;
        gc.weightx = 1.0;
        center.add(computeButton, gc);
        gc.gridwidth = 1;

        // Row 3: result label
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 18));
        resultLabel.setToolTipText("Result of the computation.");
        resultLabel.getAccessibleContext().setAccessibleName(ACC_RESULT_NAME);
        resultLabel.getAccessibleContext().setAccessibleDescription(ACC_RESULT_DESC);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 3;
        center.add(resultLabel, gc);
        gc.gridwidth = 1;

        // ===== South actions =====
        final JPanel buttons = new JPanel();
        final JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, BTN_SIZE));
        resetButton.setToolTipText(ACC_RESET_DESC);
        resetButton.getAccessibleContext().setAccessibleName(ACC_RESET_NAME);
        resetButton.getAccessibleContext().setAccessibleDescription(ACC_RESET_DESC);
        resetButton.addActionListener(this::onReset);
        buttons.add(resetButton);

        final JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, BTN_SIZE));
        exitButton.setToolTipText("Close the application.");
        exitButton.getAccessibleContext().setAccessibleName(ACC_EXIT_NAME);
        exitButton.addActionListener(this::onExit);
        buttons.add(exitButton);

        final JPanel south = new JPanel(new BorderLayout(PAD, PAD));
        south.add(buttons, BorderLayout.NORTH);
        south.add(statusLabel, BorderLayout.SOUTH);
        root.add(south, BorderLayout.SOUTH);

        // Initial focus & keyboard usability
        SwingUtilities.invokeLater(inputField::requestFocusInWindow);
        getRootPane().setDefaultButton(computeButton);
    }

    // ========= Event handlers =========

    private void onCompute(final ActionEvent e) {
        statusLabel.setText(" ");
        try {
            final double raw = Double.parseDouble(inputField.getText().trim());
            final boolean inDegrees = "Degrees".equals(unitBox.getSelectedItem());

            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, () -> String.format("Input: %.6f (%s)",
                        raw, inDegrees ? "deg" : "rad"));
            }

            // Undefined checks first (your requested behavior).
            if (isUndefinedTan(raw, inDegrees)) {
                resultLabel.setText("tan(x) = undefined");
                if (LOGGER.isLoggable(Level.INFO)) {
                    LOGGER.log(Level.INFO, "Result: undefined (multiple of 90°/π/2).");
                }
                return;
            }

            final double result = calculateTan(raw, inDegrees);
            resultLabel.setText(String.format("tan(x) = %.6f", result));

            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, () -> String.format("Result: %.6f", result));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input.");
            statusLabel.setText("Please enter a valid real number for x.");
            if (LOGGER.isLoggable(Level.WARNING)) {
                LOGGER.log(Level.WARNING, "Invalid numeric input.", ex);
            }
        } catch (Exception ex) {
            resultLabel.setText("Error.");
            statusLabel.setText("An unexpected error occurred.");
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, "Unexpected error during compute.", ex);
            }
            JOptionPane.showMessageDialog(
                    this,
                    "Something went wrong while computing tan(x).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void onReset(final ActionEvent e) {
        inputField.setText("");
        resultLabel.setText(" ");
        statusLabel.setText("Input cleared.");
        inputField.requestFocusInWindow();
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, "Reset button clicked - input cleared.");
        }
    }

    private void onExit(final ActionEvent e) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, "Exit button clicked - application closing.");
        }
        System.exit(0);
    }

    // ========= Calculation helpers (unchanged logic) =========

    /**
     * Static method to calculate tan(x) for testing.
     *
     * @param value input x value
     * @param isDegrees true if the input is in degrees
     * @return computed tan(x)
     */
    public static double calculateTan(double value, final boolean isDegrees) {
        if (isDegrees) {
            value = Math.toRadians(value);
        }
        return Math.tan(value);
    }

    /**
     * Detect undefined tan(x) at odd multiples of 90° (π/2), both signs.
     * Uses your exact requirement:
     * - degrees: Math.abs(value) % 180 == 90
     * - radians: Math.abs(value) % Math.PI == Math.PI / 2
     *
     * @param value the input value
     * @param isDegrees whether the value is in degrees
     * @return true if tan(x) is undefined at the given value
     */
    public static boolean isUndefinedTan(final double value, final boolean isDegrees) {
        final double eps = 1e-12;
        if (isDegrees) {
            final double mod = Math.abs(value) % 180.0;
            return Math.abs(mod - 90.0) < eps;
        }
        final double mod = Math.abs(value) % Math.PI;
        return Math.abs(mod - (Math.PI / 2.0)) < eps;
    }

    // ========= App entry =========

    /**
     * Main method to launch the calculator.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final TanCalculatorGUI app = new TanCalculatorGUI();
            app.setVisible(true);
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.log(Level.INFO, "Application started and GUI launched.");
            }
        });
    }
}
