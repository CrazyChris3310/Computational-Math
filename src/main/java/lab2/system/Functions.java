package lab2.system;

import lab2.equation.Expression;

public class Functions {

  @Expression(formula = "f(x, y) = 0.1x^2 + x + 0.2y^2 - 0.3")
  public static double func1(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.1*x*x + x + 0.2*y*y - 0.3;
  }

  @Expression(formula = "f(x, y) = 0.2x^2 + y - 0.1xy - 0.7")
  public static double func2(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 0.2*x*x + y - 0.1*x*y - 0.7;
  }

  @Expression(formula = "f(x, y) = 2x^2 - xy - 5x + 1")
  public static double func3(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return 2*x*x - x*y - 5*x + 1;
  }

  @Expression(formula = "f(x, y) = x + 3ln(x) - y^2")
  public static double func4(double ...vars) {
    double x = vars[0];
    double y = vars[1];
    return x + 3*Math.log(x) - y*y;
  }
}
