package lab2.system.examples;

import lab2.Expression;
import lab2.system.ManyVariableFunction;
import lab2.system.matrix.Matrix;
import lab2.system.matrix.Vector;
import lab2.system.matrix.exceptions.MethodDivergesException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public abstract class AbstractNonLinearSystem implements NonLinearSystem {

  private static final int LIMIT = 20000;
  private static final double ACCURACY = 1e-4;

  private final List<ManyVariableFunction> system = new LinkedList<>();

  protected void addEquation(ManyVariableFunction function) {
    system.add(function);
  }

  public double[] solve(double... point) {
    Matrix lambdas = findLambdasMatrix(point);

    Vector pointVector = new Vector(point);

    double delta;
    double count = 0;
    do {
      delta = 0;
      double[] funcValues = new double[system.size()];
      for (int i = 0; i < point.length; ++i) {
        funcValues[i] = system.get(i).solve(pointVector.getVector());
      }

      Vector newPointVector = pointVector.subtract(lambdas.multiply(new Vector(funcValues)));

      for (int i = 0; i < point.length; ++i) {
        if (Double.isNaN(newPointVector.get(i))) {
          throw new MethodDivergesException("Can't solve system with this method at this point "
                                            + Arrays.toString(point) +
                                            ". You may try another point "
                                            + "that is more close to real answer");
        }
        if (Math.abs(newPointVector.get(i) - pointVector.get(i)) > delta) {
          delta = Math.abs(newPointVector.get(i) - pointVector.get(i));
        }
      }
      pointVector = new Vector(newPointVector.getVector().clone());
      count += 1;
    } while (delta > ACCURACY && count < LIMIT);

    if (count >= LIMIT) {
      return null;
    }

    return pointVector.getVector();
  }

  public Matrix findLambdasMatrix(double[] point) {
    double[][] jacobian = findJacobian(point);
    Matrix lambdas = new Matrix(jacobian).getReverseMatrix();
    if (lambdas == null) {
      throw new MethodDivergesException("Jacobian matrix determinant is zero for point " +
                                        Arrays.toString(point));
    }
    for (int i = 0; i < lambdas.getMatrix().length; ++i) {
      for (int j = 0; j < lambdas.getMatrix().length; ++j) {
        if (Double.isNaN(lambdas.getElement(i, j))) {
          throw new MethodDivergesException("Can't solve system with this method at this point "
                                            + Arrays.toString(point) +
                                            ". You may try another point "
                                            + "that is more close to real answer");
        }
      }

    }
    return lambdas;
  }

  public double[][] findJacobian(double[] point) {
    double[][] jacobian = new double[point.length][point.length];
    for (int i = 0; i < point.length; ++i) {
      for (int j = 0; j < point.length; ++j) {
        jacobian[i][j] = system.get(i).partialDerivative(j, point);
      }
    }
    return jacobian;
  }

  @Override
  public int size() {
    return system.size();
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Method[] methods = this.getClass().getMethods();
    for (Method method : methods) {
      Expression expression = method.getAnnotation(Expression.class);
      if (expression != null) {
        stringBuilder.append("| ").append(expression.formula()).append(" = 0").append("\n");
      }
    }
    return stringBuilder.toString();
  }

}
