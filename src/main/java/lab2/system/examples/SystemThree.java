package lab2.system.examples;

import lab2.Expression;

public class SystemThree extends AbstractNonLinearSystem {
  public SystemThree() {
    addEquation(this::func1);
    addEquation(this::func2);
  }

  @Expression(formula = "x^2 + y^2 - 1")
  public double func1(double... vars) {
    double x = vars[0];
    double y = vars[1];
    return x * x + y * y - 1;
  }

  @Expression(formula = "x^3 - y")
  public double func2(double... vars) {
    double x = vars[0];
    double y = vars[1];
    return Math.pow(x, 3) - y;
  }
}
