package lab2.system.examples;

import lab2.Expression;
import lab2.system.ManyVariableFunction;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
public abstract class AbstractNonLinearSystem implements NonLinearSystem {

  private static final int LIMIT = 20000;
  private static final double ACCURACY = 1e-4;

  private final List<ManyVariableFunction> system = new LinkedList<>();

  protected void addEquation(ManyVariableFunction function) {
    system.add(function);
  }

  private double[] findLambdas(double[] point) {
    double[] lambdas = new double[point.length];
    for (int i = 0; i < point.length; ++i) {
      double a = point[i] - 0.5;
      double b = point[i] + 0.5;
      double[] temp = point.clone();
      ManyVariableFunction f = system.get(i);
      double max_derivative = f.partialDerivative(i, temp);
      for (double dx = a; dx <= b; dx += 0.01) {
        temp[i] = dx;
        double der = f.partialDerivative(i, temp);
        if (der > max_derivative)
          max_derivative = der;
      }
      lambdas[i] = -1 / max_derivative;
    }
    return lambdas;
  }

  private double findQuitParameter(double[] lambdas, double[] point) {
    double max = 0;
    for (int i = 0; i < system.size(); ++i) {
      double sum = 0;
      for (int j = 0; j < point.length; ++j) {
        double temp = i == j ? 1 : 0;
        temp += lambdas[i] * system.get(i).partialDerivative(j, point);
        sum += Math.abs(temp);
      }
      if (sum > max)
        max = sum;
    }
    return max;
  }

  public double[] findSolution(double ... point) {

    double[] lambdas = findLambdas(point);

    double q = findQuitParameter(lambdas, point);
    if (q > 1)
      return null;

    double[] newAns = new double[point.length];
    double delta;
    double count = 0;
    do {
      delta = 0;
      for (int i = 0; i < point.length; ++i) {
        newAns[i] = point[i] + lambdas[i] * system.get(i).solve(point);
        if (Math.abs(newAns[i] - point[i]) > delta)
          delta = Math.abs(newAns[i] - point[i]);
      }
      point = newAns.clone();
      count += 1;
    } while (delta > ACCURACY && count < LIMIT);

    return newAns;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Method[] methods = this.getClass().getMethods();
    for (Method method: methods) {
      Expression expression = method.getAnnotation(Expression.class);
      if (expression != null) {
        stringBuilder.append("| ").append(expression.formula()).append(" = 0").append("\n");
      }
    }
    return stringBuilder.toString();
  }

}
