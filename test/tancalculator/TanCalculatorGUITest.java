package tancalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

final class TanCalculatorGUITest {

    private static final double DELTA = 1e-6;

    // -------- Existing “happy path” tests --------

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
        assertEquals(1.0, TanCalculatorGUI.calculateTan(Math.PI / 4.0, false), DELTA);
    }

    @Test
    void test45Degrees() {
        assertEquals(1.0, TanCalculatorGUI.calculateTan(45.0, true), DELTA);
    }

    @Test
    void testNegativePiOver4Radians() {
        assertEquals(-1.0, TanCalculatorGUI.calculateTan(-Math.PI / 4.0, false), DELTA);
    }

    @Test
    void testNegative45Degrees() {
        assertEquals(-1.0, TanCalculatorGUI.calculateTan(-45.0, true), DELTA);
    }

    // -------- Near the asymptote (±90° / ±π/2) --------
    // For degrees: 89.999° is ~1.745e-5 rad from π/2 ⇒ tan ≈ 57,295.
    // So we assert a realistic lower bound (1e4), plus sign behavior.

    @Test
    void testNear90DegreesLargeMagnitudePositiveSide() {
        double result = TanCalculatorGUI.calculateTan(89.999, true);
        assertTrue(Math.abs(result) > 1e4, "Expected very large |tan(89.999°)|");
        // Left side of 90° (approaching from below) -> +∞
        assertTrue(result > 0.0, "Expected positive large value");
    }

    @Test
    void testNear90DegreesLargeMagnitudeNegativeSide() {
        double result = TanCalculatorGUI.calculateTan(90.001, true);
        assertTrue(Math.abs(result) > 1e4, "Expected very large |tan(90.001°)|");
        // Right side of 90° (just above) -> -∞
        assertTrue(result < 0.0, "Expected negative large value");
    }

    @Test
    void testNearPiOver2RadiansLargeMagnitudePositiveSide() {
        double x = (Math.PI / 2.0) - 1e-6;
        double result = TanCalculatorGUI.calculateTan(x, false);
        assertTrue(Math.abs(result) > 1e6, "Expected very large |tan(π/2 - ε)|");
        assertTrue(result > 0.0, "Expected positive large value");
    }

    @Test
    void testNearPiOver2RadiansLargeMagnitudeNegativeSide() {
        double x = (Math.PI / 2.0) + 1e-6;
        double result = TanCalculatorGUI.calculateTan(x, false);
        assertTrue(Math.abs(result) > 1e6, "Expected very large |tan(π/2 + ε)|");
        assertTrue(result < 0.0, "Expected negative large value");
    }

    // -------- Undefined detection (your helper) --------

    @Test
    void testUndefinedAt90Degrees() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(90.0, true));
    }

    @Test
    void testUndefinedAtMinus90Degrees() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(-90.0, true));
    }

    @Test
    void testUndefinedAt270Degrees() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(270.0, true));
    }

    @Test
    void testUndefinedAt450Degrees() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(450.0, true));
    }

    @Test
    void testUndefinedAtPiOver2() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(Math.PI / 2.0, false));
    }

    @Test
    void testUndefinedAtMinusPiOver2() {
        assertTrue(TanCalculatorGUI.isUndefinedTan(-Math.PI / 2.0, false));
    }

    // -------- Should NOT be undefined (close, but not exact) --------

    @Test
    void testNotUndefinedAt89Point999Degrees() {
        assertFalse(TanCalculatorGUI.isUndefinedTan(89.999, true));
    }

    @Test
    void testNotUndefinedAtPiOver2MinusEps() {
        assertFalse(TanCalculatorGUI.isUndefinedTan((Math.PI / 2.0) - 1e-9, false));
    }

    // -------- Periodicity: tan(θ + 180k°) == tan(θ); tan(x + kπ) == tan(x) --------

    @Test
    void testPeriodicityDegrees() {
        double base = 30.0;
        double k1 = TanCalculatorGUI.calculateTan(base, true);
        double k2 = TanCalculatorGUI.calculateTan(base + 180.0, true);
        double k3 = TanCalculatorGUI.calculateTan(base - 360.0, true);
        assertEquals(k1, k2, DELTA);
        assertEquals(k1, k3, DELTA);
    }

    @Test
    void testPeriodicityRadians() {
        double base = 0.7; // arbitrary
        double k1 = TanCalculatorGUI.calculateTan(base, false);
        double k2 = TanCalculatorGUI.calculateTan(base + Math.PI, false);
        double k3 = TanCalculatorGUI.calculateTan(base - 2.0 * Math.PI, false);
        assertEquals(k1, k2, DELTA);
        assertEquals(k1, k3, DELTA);
    }

    // -------- Symmetry: tan(-x) == -tan(x) --------

    @Test
    void testOddSymmetryDegrees() {
        double t = TanCalculatorGUI.calculateTan(33.0, true);
        double tn = TanCalculatorGUI.calculateTan(-33.0, true);
        assertEquals(-t, tn, DELTA);
    }

    @Test
    void testOddSymmetryRadians() {
        double t = TanCalculatorGUI.calculateTan(0.3, false);
        double tn = TanCalculatorGUI.calculateTan(-0.3, false);
        assertEquals(-t, tn, DELTA);
    }

    // -------- Small-angle behavior (tan x ~ x for small x in radians) --------

    @Test
    void testVerySmallAngleRadians() {
        double x = 1e-10;
        assertEquals(x, TanCalculatorGUI.calculateTan(x, false), 1e-10);
    }

    // -------- Robustness: large magnitudes reduce correctly --------

    @Test
    void testVeryLargeDegrees() {
        double deg = 123456789.0;
        double expected = Math.tan(Math.toRadians(deg));
        assertEquals(expected, TanCalculatorGUI.calculateTan(deg, true), DELTA);
    }

    @Test
    void testLargeRadians() {
        double rad = 1e6;
        double expected = Math.tan(rad);
        assertEquals(expected, TanCalculatorGUI.calculateTan(rad, false), DELTA);
    }
}
