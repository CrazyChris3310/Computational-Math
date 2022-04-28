package lab3;

import lab1.utils.Input;
import lab2.Expression;
import lab2.Functions;
import lab3.exceptions.FunctionNotDefinedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.function.Function;

public class Main {
  private static final Input input = new Input();

  public static void main(String[] args) {
    resolveEquation();
  }

  private static void resolveEquation() {
    Function<Double, Double> func = selectEquation(Functions.class);
    System.out.print("Enter left border: ");
    double a = input.readDouble();
    System.out.print("Enter right border: ");
    double b = input.readDouble();
    Solver solver = new Solver();
    double[][] result;
    try {
      result = solver.solve(func, a, b);
    } catch (FunctionNotDefinedException e) {
      System.out.println(e.getMessage());
      return;
    }

    System.out.printf(Locale.ROOT, "Left: %.5f\n", result[0][0]);
    System.out.println("Steps: " + (int) result[0][1]);
    System.out.printf(Locale.ROOT, "Right: %.5f\n", result[1][0]);
    System.out.println("Steps: " + (int) result[1][1]);
    System.out.printf(Locale.ROOT, "Middle: %.5f\n", result[2][0]);
    System.out.println("Steps: " + (int) result[2][1]);
  }

  public static Function<Double, Double> selectEquation(Class<?> clazz) {
    StringBuilder builder = new StringBuilder();
    Method[] functions = clazz.getDeclaredMethods();
    for (int i = 0; i < functions.length; ++i) {
      Expression expression = functions[i].getAnnotation(Expression.class);
      if (expression != null) {
        builder.append(i + 1).append(". f(x) = ").append(expression.formula()).append("\n");
      }
    }
    System.out.print(builder);
    System.out.print("Select an equation to solve: ");
    int n = input.readInt();
    return x -> {
      Object num;
      try {
        num = functions[n - 1].invoke(null, x);
      } catch (IllegalAccessException | InvocationTargetException e) {
        num = null;
      }
      return (Double) num;
    };
  }
}
