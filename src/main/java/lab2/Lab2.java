package lab2;

import lab2.equation.Expression;
import lab2.equation.Func;
import lab2.equation.Solver;
import lab2.system.NonLinearSystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
public class Lab2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("1. Equation");
    System.out.println("2. System");
    System.out.print("Do you want to solve an equation or a system? ");
    int n = sc.nextInt();

    if (n == 1) {
      Func func = selectEquation(sc);
      System.out.print("Enter left border: ");
      double a = sc.nextDouble();
      System.out.print("Enter right border: ");
      double b = sc.nextDouble();
      Solver solver = new Solver();
      double[][] result = solver.solveEquation(func, a, b);

      System.out.println("Newton method: ");
      for (int i = 0; i < result[0].length; ++i) {
        System.out.printf("x" + (i + 1) +  " = %.4f\n", result[0][i]);
      }
      System.out.println("Iteration method: ");
      for (int i = 0; i < result[0].length; ++i) {
        System.out.printf("x" + (i + 1) +  " = %.4f\n", result[1][i]);
      }
    } else {
      NonLinearSystem nls = selectSystem(sc);
      System.out.println("Enter approximate solution");
      System.out.print("x: ");
      double x = sc.nextDouble();
      System.out.print("y: ");
      double y = sc.nextDouble();

      double[] solution = nls.findSolution(x, y);
      if (solution == null) {
        System.out.println("System can't be solved with iterations method");
      } else {
        System.out.println("x = " + solution[0]);
        System.out.println("y = " + solution[1]);
      }
    }

  }

  public static Func selectEquation(Scanner sc) {
    StringBuilder builder = new StringBuilder();
    Method[] functions = Functions.class.getDeclaredMethods();
    for (int i = 0; i < functions.length; ++i) {
      Expression expression = functions[i].getAnnotation(Expression.class);
      if (expression != null) {
        builder.append(i + 1).append(". ").append(expression.formula()).append(" = 0").append("\n");
      }
    }
    System.out.print(builder);
    System.out.print("Select an equation to solve: ");
    int n = sc.nextInt();
    return x -> {
      Object num;
      try {
        num = functions[n - 1].invoke(null, x);
      } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
        num = null;
      }
      return (Double) num;
    };
  }

  public static NonLinearSystem selectSystem(Scanner sc) {
    System.out.println();
    for (int i = 0; i < NonLinearSystem.NON_LINEAR_SYSTEMS.size(); ++i) {
      System.out.println(i + ".");
      System.out.print(NonLinearSystem.NON_LINEAR_SYSTEMS.get(i).toString());
    }
    System.out.print("Select a system to solve: ");
    int n = sc.nextInt();
    return NonLinearSystem.NON_LINEAR_SYSTEMS.get(n - 1);
  }
}
