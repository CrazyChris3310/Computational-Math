package lab2.system.examples;

public interface NonLinearSystem {

  double[] solve(double... point);

  int size();
}
