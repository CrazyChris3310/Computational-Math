package lab2;

import lab1.utils.Input;
import lab2.equation.Func;
import lab2.equation.Solver;
import lab2.system.examples.NonLinearSystem;
import lab2.system.examples.SystemOne;
import lab2.system.examples.SystemThree;
import lab2.system.examples.SystemTwo;
import lab2.system.matrix.exceptions.MethodDivergesException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class Lab2 {

  private static final List<NonLinearSystem> systems = new LinkedList<>();
  private static final Input input = new Input();

  static {
    systems.add(new SystemOne());
    systems.add(new SystemTwo());
    systems.add(new SystemThree());
  }

  public static void main(String[] args) {

    System.out.println("1. Equation");
    System.out.println("2. System");
    System.out.print("Do you want to solve an equation or a system? ");
    int n = input.readInt();

    if (n == 1) {
      resolveEquation();
    } else {
      resolveSystem();
    }
  }

  private static void resolveEquation() {
    Func func = selectEquation(Functions.class);
    System.out.print("Enter left border: ");
    double a = input.readDouble();
    System.out.print("Enter right border: ");
    double b = input.readDouble();
    Solver solver = new Solver();
    double[][] result = solver.solveEquation(func, a, b);

    if (result == null) {
      System.out.println("No roots were found on this interval");
      return;
    }
    System.out.println("Newton method: ");
    if (result[0] != null) {
      for (int i = 0; i < result[0].length; ++i) {
        System.out.printf("x" + (i + 1) + " = %.4f\n", result[0][i]);
      }
    } else {
      System.out.println("Can't solve equation with this method");
    }
    System.out.println("Iteration method: ");
    if (result[0] != null) {
      for (int i = 0; i < result[0].length; ++i) {
        System.out.printf("x" + (i + 1) + " = %.4f\n", result[1][i]);
      }
    } else {
      System.out.println("Can't solve equation with this method");
    }
  }

  private static void resolveSystem() {
    NonLinearSystem nls = selectSystem();
    System.out.println("Enter approximate solution");
    System.out.print("x: ");
    double x = input.readDouble();
    System.out.print("y: ");
    double y = input.readDouble();

    double[] solution;
    try {
      solution = nls.solve(x, y);
    } catch (MethodDivergesException e) {
      System.out.println(e.getMessage());
      return;
    }

    if (solution == null) {
      System.out.println("System can't be solved with iterations method");
    } else {
      System.out.println("x = " + solution[0]);
      System.out.println("y = " + solution[1]);
    }
  }

  public static Func selectEquation(Class<?> clazz) {
    StringBuilder builder = new StringBuilder();
    Method[] functions = clazz.getDeclaredMethods();
    for (int i = 0; i < functions.length; ++i) {
      Expression expression = functions[i].getAnnotation(Expression.class);
      if (expression != null) {
        builder.append(i + 1).append(". ").append(expression.formula()).append(" = 0").append("\n");
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

  public static NonLinearSystem selectSystem() {
    for (int i = 0; i < systems.size(); ++i) {
      System.out.println((i + 1) + ".");
      System.out.print(systems.get(i).toString());
    }
    System.out.print("\nSelect a system to solve: ");
    int n = input.readInt();
    return systems.get(n - 1);
  }
}
