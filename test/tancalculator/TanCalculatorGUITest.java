package tancalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
    void testNear90DegreesLargeMagnitude() {
        // 90° is undefined; use 89.999° and just assert it's very large in magnitude
        double result = TanCalculatorGUI.calculateTan(89.999, true);
        assertTrue(Math.abs(result) > 1e6);
    }
}
