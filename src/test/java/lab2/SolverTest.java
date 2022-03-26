package lab2;

import lab2.equation.Solver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private double accuracy = 1e-4;
    private Solver solver = new Solver();

    @Test
    void findBordersSinus() {
        double[][] borders = solver.findBorders(Functions::sinus, -100, 100);
        assertEquals(1, borders.length);
        assertTrue(Math.abs(0.6 -borders[0][0]) <= accuracy);
        assertTrue(Math.abs(0.7 -borders[0][1]) <= accuracy);
    }

    @Test
    void findBordersLog() {
        double[][] borders = solver.findBorders(Functions::logarithmic, -50, 50);
        assertEquals(1, borders.length);
        assertTrue(Math.abs(0.9 - borders[0][0]) < accuracy);
        assertTrue(Math.abs(1.1 - borders[0][1]) <= accuracy);
    }

    @Test
    void findBordersExponent() {
        double[][] borders = solver.findBorders(Functions::exponent, -50, 50);
        assertEquals(2, borders.length);
        assertTrue(Math.abs(-3.5 - borders[0][0]) <= accuracy);
        assertTrue(Math.abs(-3.4 - borders[0][1]) <= accuracy);
        assertTrue(Math.abs(1.1 -borders[1][0]) <= accuracy);
        assertTrue(Math.abs(1.2 - borders[1][1]) <= accuracy);
    }

    @Test
    void findBordersThirdGrade() {
        double[][] borders = solver.findBorders(Functions::thirdGrade, -50, 50);
        assertEquals(3, borders.length);
        assertTrue(Math.abs(-1.9 - borders[0][0]) <= accuracy, "Expected: " + -1.9 + "\nReceived: " + borders[0][0]);
        assertTrue(Math.abs(-1.8 - borders[0][1]) <= accuracy);
        assertTrue(Math.abs(-0.3 - borders[1][0]) <= accuracy);
        assertTrue(Math.abs(-0.2 - borders[1][1]) <= accuracy);
        assertTrue(Math.abs(0.4 - borders[2][0]) <= accuracy);
        assertTrue(Math.abs(0.5 - borders[2][1]) <= accuracy);
    }

    @Test
    void solveEquation() {
    }

    @Test
    void solveNewtonSinus() {
        Double res = solver.solveNewton(Functions::sinus, 0.6, 0.7);
        assertTrue(Math.abs(res - 0.6691) <= accuracy);
    }

    @Test
    void solveNewtonLog() {
        Double res = solver.solveNewton(Functions::logarithmic, 0.9, 1.1);
        assertTrue(Math.abs(res - 1) <= accuracy);
    }

    @Test
    void solveNewtonThirdGrade() {
        Double res = solver.solveNewton(Functions::thirdGrade, -1.9, -1.8);
        assertTrue(Math.abs(res - -1.80457) <= accuracy);
    }

    @Test
    void solveIterationsSinus() {
        Double res = solver.solveIterations(Functions::sinus, 0.6, 0.7);
        assertTrue(Math.abs(res - 0.6691) <= accuracy);
    }

    @Test
    void solveIterationsLog() {
        Double res = solver.solveIterations(Functions::logarithmic, 0.9, 1.1);
        assertTrue(Math.abs(res - 1) <= accuracy);
    }

    @Test
    void solveIterationsThirdGrade() {
        Double res = solver.solveIterations(Functions::thirdGrade, -1.9, -1.8);
        assertTrue(Math.abs(res - -1.80457) <= accuracy);
    }

}