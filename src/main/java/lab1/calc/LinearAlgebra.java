package lab1.calc;

public class LinearAlgebra {

  private static final int MAX_ITERATIONS = 10000;

  public static Result solve(EqSystem sys, double accuracy) {

    Result result = new Result();

    double[] answers = new double[sys.getSize()];
    double[] errors = new double[sys.getSize()];
    int count = 0;
    long start = System.nanoTime();
    do {
      for (int i = 0; i < sys.getSize(); ++i) {
        double sum = sys.getB(i);
        for (int j = 0; j < i; ++j) {
          sum -= sys.getElement(i, j) * answers[j];
        }
        for (int j = i + 1; j < sys.getSize(); ++j) {
          sum -= sys.getElement(i, j) * answers[j];
        }
        sum /= sys.getElement(i, i);
        errors[i] = Math.abs(sum - answers[i]);
        answers[i] = sum;
        if (!Double.isFinite(answers[i])) {
          result.setValid(false);
          result.setDuration((System.nanoTime() - start) / 1000);
          return result;
        }
      }
      count += 1;
    } while (!isAccurate(errors, accuracy) && count < MAX_ITERATIONS);

    if (count < MAX_ITERATIONS) {
      result.setValid(true);
      result.setDuration((System.nanoTime() - start) / 1000);
      result.setAns(answers);
      result.setIterationCount(count);
      result.setError(errors);
    } else {
      result.setValid(false);
    }

    return result;
  }

  public static boolean isAccurate(double[] errors, double accuracy) {
    for (double error : errors) {
      if (error > accuracy) {
        return false;
      }
    }
    return true;
  }

  public static boolean isAccurate(double[] oldAnswers, double[] newAnswers, double accuracy) {
    for (int i = 0; i < oldAnswers.length; ++i) {
      if (Math.abs(oldAnswers[i] - newAnswers[i]) > accuracy) {
        return false;
      }
    }
    return true;
  }
}
