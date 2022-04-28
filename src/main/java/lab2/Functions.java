package lab2;

public class Functions {

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

  @Expression(formula = "3x^3 + 5sin(x) - 4")
  public static double sinus(double x) {
    return 3 * Math.pow(x, 3) + 5 * Math.sin(x) - 4;
  }

  @Expression(formula = "10ln(x)")
  public static double logarithmic(double x) {
    return 10 * Math.log(x);
  }

  @Expression(formula = "sin(x)/x")
  public static double sinDivideX(double x) {
    return Math.sin(x) / x;
  }

  @Expression(formula = "1/x")
  public static double hyperbola(double x) {
    return 1 / x;
  }

}