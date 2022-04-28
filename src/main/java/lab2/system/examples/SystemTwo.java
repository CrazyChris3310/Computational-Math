package lab2.system.examples;

import lab2.Expression;

public class SystemTwo extends AbstractNonLinearSystem {

  public SystemTwo() {
    addEquation(this::func1);
    addEquation(this::func2);
  }

  @Expression(formula = "2x^2 - xy - 5x + 1")
  public double func1(double... vars) {
    double x = vars[0];
    double y = vars[1];
    return 2 * x * x - x * y - 5 * x + 1;
  }

  @Expression(formula = "x + 3ln(x) - y^2")
  public double func2(double... vars) {
    double x = vars[0];
    double y = vars[1];
    return x + 3 * Math.log(x) - y * y;
  }

}
