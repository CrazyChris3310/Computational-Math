package lab2.system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonLinearSystemTest {

    private double f(double ... vars) {
        double x = vars[0];
        double y = vars[1];
        return x*x + y*y - 4;
    }

    private double g(double ... vars) {
        double x = vars[0];
        double y = vars[1];
        return y - 3*x*x;
    }

    private double h(double ...vars) {
        double x = vars[0];
        double y = vars[1];
        return 0.1*x*x + x + 0.2*y*y - 0.3;
    }

    private double phi(double ...vars) {
        double x = vars[0];
        double y = vars[1];
        return 0.2*x*x + y - 0.1*x*y - 0.7;
    }

    @Test
    void solveIterations() {
        NonLinearSystem sys = new NonLinearSystem();
        sys.addFunction(this::f);
        sys.addFunction(this::g);

        double[] ans = sys.findSolution(1, 2);
        double[] exp = new double[] { 0.808, 1.846 };

        assertArrayEquals(exp, ans);
    }
}