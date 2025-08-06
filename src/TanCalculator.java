import java.util.Scanner;

public class TanCalculator {

    // Computes tan(x), ensuring undefined points (odd multiples of π/2) are checked
    public static String computeTan(double x) {
        double n = Math.round(x / (Math.PI / 2));

        if (n % 2 != 0) {  // Odd multiple of π/2
            double approx = n * (Math.PI / 2);
            if (Math.abs(x - approx) < 1e-4) {  // Allow a tolerance
                return "tan(x) is undefined at x = " + x;
            }
        }

        double result = Math.tan(x);
        return String.format("tan(%.6f) = %.6f", x, result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the tan(x) Calculator!");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter a real number x (in radians): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator. Goodbye!");
                break;
            }

            try {
                double x = Double.parseDouble(input);
                String result = computeTan(x);
                System.out.println(result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid real number.");
            }
        }

        scanner.close();
    }
}
