package tancalculator;

import java.util.Scanner;
import java.util.logging.Logger;

public class TanCalculator {

    private static final Logger logger = Logger.getLogger(TanCalculator.class.getName());

    // Computes tan(x), ensuring undefined points (odd multiples of π/2) are checked
    public static String computeTan(double x) {
        final int n = (int) Math.round(x / (Math.PI / 2));

        if (n % 2 != 0) { // Odd multiple of π/2
            final double approx = n * (Math.PI / 2);
            if (Math.abs(x - approx) < 1e-4) { // Allow a tolerance
                return "tan(x) is undefined at x = " + x;
            }
        }

        final double result = Math.tan(x);
        return "tan(x) = " + result;
    }

    // Example main to test the function
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        logger.info("Enter a value for x:");
        final double x = scanner.nextDouble();

        final String result = computeTan(x);
        logger.info(result);

        scanner.close();
    }
}
