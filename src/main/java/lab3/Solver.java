package lab3;

import lab3.exceptions.FunctionNotDefinedException;

import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;

public class Solver {
  public static final double EPS = 1e-4;

  public double[][] solve(Function<Double, Double> func, double a, double b) {
    return solve(func, a, b, EPS);
  }

  public double[][] solve(Function<Double, Double> func, double a, double b, double eps) {
    double[][] result = new double[3][];

    result[0] = solveAndFindStepsAmount((x, dx) -> func.apply(x), a, b, eps);
    result[1] = solveAndFindStepsAmount((x, dx) -> func.apply(x + dx), a, b, eps);
    result[2] = solveAndFindStepsAmount((x, dx) -> func.apply(x + dx / 2), a, b, eps);

    return result;
  }

  private double[] solveAndFindStepsAmount(ToDoubleBiFunction<Double, Double> func,
                                           double a, double b, double eps) {
    int steps_count = 1;
    double error;
    double first;
    double second;
    do {
      double dx1 = (b - a) / steps_count;
      first = solveBasic(x -> func.applyAsDouble(x, dx1), a, b, dx1);
      steps_count *= 2;
      double dx2 = (b - a) / steps_count;
      second = solveBasic(x -> func.applyAsDouble(x, dx2), a, b, dx2);
      error = Math.abs(first - second) / 3.0;
    } while (error > eps);
    return new double[]{ second, steps_count };
  }

  private double solveBasic(Function<Double, Double> func, double a, double b, double dx) {
    double tens = Math.pow(10, -Math.log10(Double.min(EPS, dx)));
    double sum = 0;
    double i = Math.round(a * tens) / tens;
    while (i < b) {
      double part = func.apply(i);
      if (isBreakPoint(part)) {
        double left = func.apply(i - dx);
        double right = func.apply(i + dx);
        if (Double.isNaN(left) || Double.isNaN(right)) {
          throw new FunctionNotDefinedException("Function is not defined on this interval " +
                                                "[" + a + "; " + b + "]");
        }
        if (Double.isNaN(part)) {
          part = (left + right) / 2;
        } else {
          throw new FunctionNotDefinedException(
                  "Function has a second type break on this interval " +
                  "[" + a + "; " + b + "] at point " + i);
        }
      }
      sum += part * dx;
      i = Math.round((i + dx) * tens) / tens;
    }
    return sum;
  }

  private boolean isBreakPoint(double y) {
    return Double.isNaN(y) || Double.isInfinite(y);
  }

}
