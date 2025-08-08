package tancalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TanCalculatorGUITest {

    private static final double DELTA = 1e-6;

    @Test
    void testZeroRadians() {
        assertEquals(0.0, TanCalculatorGUI.calculateTan(0.0, false), DELTA);
    }

    @Test
    void testZeroDegrees() {
        assertEquals(0.0, TanCalculatorGUI.calculateTan(0.0, true), DELTA);
    }

    @Test
    void testPiOver4Radians() {
        assertEquals(1.0, TanCalculatorGUI.calculateTan(Math.PI / 4, false), DELTA);
    }

    @Test
    void test45Degrees() {
        assertEquals(1.0, TanCalculatorGUI.calculateTan(45.0, true), DELTA);
    }

    @Test
    void testNegativePiOver4Radians() {
        assertEquals(-1.0, TanCalculatorGUI.calculateTan(-Math.PI / 4, false), DELTA);
    }

    @Test
    void testNegative45Degrees() {
        assertEquals(-1.0, TanCalculatorGUI.calculateTan(-45.0, true), DELTA);
    }

    @Test
    void test180Degrees() {
        assertEquals(0.0, TanCalculatorGUI.calculateTan(180.0, true), DELTA);
    }

    @Test
    void testVerySmallValueRadians() {
        double smallValue = 1e-10;
        assertEquals(Math.tan(smallValue), TanCalculatorGUI.calculateTan(smallValue, false), DELTA);
    }

    @Test
    void testVeryLargeValueRadians() {
        double largeValue = 1e6;
        assertEquals(Math.tan(largeValue), TanCalculatorGUI.calculateTan(largeValue, false), DELTA);
    }

    @Test
    void test90DegreesInfinity() {
        double result = TanCalculatorGUI.calculateTan(90.0, true);
        boolean isCloseToInfinity = Math.abs(result) > 1e6;
        assertEquals(true, isCloseToInfinity);
    }
}
