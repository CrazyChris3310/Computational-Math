package lab2.system.examples;

import lab2.Expression;

public class SystemOne extends AbstractNonLinearSystem {

  public SystemOne() {
    addEquation(this::func1);
    addEquation(this::func2);
  }

  @Expression(formula = "0.1x^2 + x + 0.2y^2 - 0.3")
  public double func1(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.1*x*x + x + 0.2*y*y - 0.3;
  }

  @Expression(formula = "0.2x^2 + y - 0.1xy - 0.7")
  public double func2(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.2*x*x + y - 0.1*x*y - 0.7;
  }

}
