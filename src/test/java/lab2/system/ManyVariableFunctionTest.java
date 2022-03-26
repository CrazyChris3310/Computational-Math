package lab2.system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ManyVariableFunctionTest {

  private double h(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.1*x*x + x + 0.2*y*y - 0.3;
  }

  private double phi(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.2*x*x + y + 0.1*x*y - 0.7;
  }

  @Test
  void partialDerivativeX() {
    ManyVariableFunction f = this::h;

    double ans = f.partialDerivative(0, 0, 1);
    double exp = 1;
    assertTrue(Math.abs(exp - ans) < 1e-3);

    ans = f.partialDerivative(0, 0.5, 1);
    exp = 1.1;
    assertTrue(Math.abs(exp - ans) < 1e-3);
  }

  @Test
  void partialDerivativeY() {
    ManyVariableFunction f = this::h;

    double ans = f.partialDerivative(1, 0, 1);
    double exp = 0.4;
    assertTrue(Math.abs(exp - ans) < 1e-3);

    ans = f.partialDerivative(1, 0, 1.5);
    exp = 0.6;
    assertTrue(Math.abs(exp - ans) < 1e-3);
  }

}