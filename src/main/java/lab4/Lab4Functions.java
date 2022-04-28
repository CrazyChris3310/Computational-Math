package lab4;

import lab2.Expression;

public class Lab4Functions {

  @Expression(formula = "5x^3 + 8x^2 -2.4x - 1")
  public static double thirdGrade(double x) {
    return 5 * Math.pow(x, 3) + 8 * Math.pow(x, 2) - 2.4 * x - 1;
  }

  @Expression(formula = "14x^4 - 4x^2 + 7x - 3")
  public static double fourthGrade(double x) {
    return 14 * Math.pow(x, 4) - 4 * x * x + 7 * x - 3;
  }

  @Expression(formula = "e^(2x) + x^2 - 12")
  public static double exponent(double x) {
    return Math.exp(2 * x) + x * x - 12;
  }

  @Expression(formula = "10ln(x)")
  public static double logarithm(double x) {
    return 10 * Math.log10(x);
  }

  @Expression(formula = "sin(2x)")
  public static double sinus(double x) {
    return Math.sin(2 * x);
  }

  @Expression(formula = "sin(3*cos(x))")
  public static double sincos(double x) {
    return Math.sin(3 * Math.cos(x));
  }
}
