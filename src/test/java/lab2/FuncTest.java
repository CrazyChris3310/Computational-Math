package lab2;

import lab2.equation.Func;
import org.junit.jupiter.api.Test;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;

class FuncTest {

    double accuracy = 1e-6;

    double derivative(Func f, double x) {
        return f.derivative(1, x);
    }

    double secondDerivative(Func f, double x) {
        return f.derivative(2, x);
    }

    @Test
    void sinusDerivative() {
        double x = 4;
        double result = derivative(Functions::sinus, x);
        double exp = 9 * x*x + 5*Math.cos(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void sinusSecondDerivative() {
        double x = Math.PI / 2;
        double result = secondDerivative(Functions::sinus, x);
        double exp = 18 * x - 5 * Math.sin(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void logDerivative() {
        double x = 4;
        double result = derivative(Functions::logarithmic, x);
        double exp = 10 / Math.abs(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void logSecondDerivative() {
        double x = 4;
        double result = secondDerivative(Functions::logarithmic, x);
        double exp = -10 / Math.pow(x, 2);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void logDerivativeNegative() {
        double x = -3;
        double result = derivative(Functions::logarithmic, x);
        double exp = NaN;
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertEquals(exp, result);
    }

    @Test
    void logSecondDerivativeNegative() {
        double x = -3;
        double result = secondDerivative(Functions::logarithmic, x);
        double exp = NaN;
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertEquals(exp, result);
    }

    @Test
    void logDerivativeAny() {
        double x = 4;
        Func f = Functions::logarithmic;
        double result = f.derivative(1, x);
        double exp = 10 / Math.abs(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void logSecondDerivativeAny() {
        double x = 4;
        Func f = Functions::logarithmic;
        double result = f.derivative(2,x);
        double exp = -10 / Math.pow(x, 2);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void sinusDerivativeAny() {
        double x = 4;
        Func f = Functions::sinus;
        double result = f.derivative(1, x);
        double exp = 9 * x*x + 5*Math.cos(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }

    @Test
    void sinusSecondDerivativeAny() {
        double x = Math.PI / 2;
        Func f = Functions::sinus;
        double result = f.derivative(2, x);
        double exp = 18 * x - 5 * Math.sin(x);
        System.out.println("Received: " + result);
        System.out.println("Expected: " + exp);
        assertTrue(Math.abs(exp - result) < accuracy);
    }
}