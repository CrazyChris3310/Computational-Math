import lab1.calc.EqSystem;
import lab1.calc.LinearAlgebra;
import lab1.calc.Result;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinearAlgebraTest {

  private final double MIN = -100;
  private final double MAX = 100;

  @Test
  void testIsAccurate() {
    double[] first = new double[]{ 1, 2, 3, 4 };
    double[] second = new double[first.length];

    double accuracy = 1e-8;
    for (int i = 0; i < first.length; ++i) {
      second[i] = first[i] + (Math.random() > 0.5 ? 1 : -1) * accuracy / 2;
    }

    boolean temp = LinearAlgebra.isAccurate(first, second, accuracy);
    assertTrue(temp);
  }

  @Test
  void testIsInaccurate() {
    double[] first = new double[]{ 1, 2, 3, 4 };
    double[] second = new double[first.length];

    double accuracy = 1e-8;
    for (int i = 0; i < first.length; ++i) {
      second[i] = first[i] + (Math.random() > 0.5 ? 1 : -1) * accuracy / 2;
    }

    second[3] += 3 * accuracy;

    boolean temp = LinearAlgebra.isAccurate(first, second, accuracy);
    assertFalse(temp);
  }

  @Test
  void solveWithLowAccuracy() {
    double accuracy = 1e-2;
    boolean result = solve(accuracy);
    assertTrue(result);
  }

  boolean solve(double accuracy) {
    double[] ans = generateAnswers();
    EqSystem sys = generateSystem(ans);
    Result result = LinearAlgebra.solve(sys, accuracy);

    System.out.println("Size: " + ans.length);
    System.out.println("Duration: " + result.getDuration() + " mcs");

    boolean temp = LinearAlgebra.isAccurate(ans, result.getAns(), accuracy);
    System.out.println("Iterations count: " + result.getIterationCount());
    System.out.println("com.calc.Result:");
    System.out.println("Expected: " + Arrays.toString(ans));
    System.out.println("Received: " + Arrays.toString(result.getAns()));
    System.out.println();
    return temp;
  }

  private double[] generateAnswers() {
    int size = (int) (Math.random() * 21);
    double[] ans = new double[size];
    for (int i = 0; i < size; ++i) {
      ans[i] = Math.random() * (MAX - MIN) + MIN;
    }
    return ans;
  }

  private EqSystem generateSystem(double[] ans) {
    EqSystem sys = new EqSystem(ans.length);
    for (int i = 0; i < sys.getSize(); ++i) {
      double sum = 0;
      for (int j = 0; j < sys.getSize(); ++j) {
        double num = Math.random() * (MAX - MIN) + MIN;
        sum += Math.abs(num);
        sys.setElement(i, j, num);
      }
      sum -= Math.abs(sys.getElement(i, i));
      sys.setElement(i, i, sum + Math.abs(Math.random() * (MAX - MIN) + MIN));
    }
    for (int i = 0; i < sys.getSize(); ++i) {
      double val = 0;
      for (int j = 0; j < sys.getSize(); ++j) {
        val += sys.getElement(i, j) * ans[j];
      }
      sys.setB(i, val);
    }
    return sys;
  }

  @Test
  void solveMany() {
    for (int i = 0; i < 50; ++i) {
      solveWithHighAccuracy();
    }
  }

  @Test
  void solveWithHighAccuracy() {
    double accuracy = 1e-8;
    boolean result = solve(accuracy);
    assertTrue(result);
  }
}