package lab2.equation;

public interface Func {

  double EPS = 1e-4;

  double solve(double x);

  default double derivative(double x) {
    return this.derivative(1, x);
  }

  default Func derivative(Func func) {
    return (e) -> (func.solve(e + EPS) - func.solve(e - EPS)) / (2 * EPS);
  }

  default double derivative(int order, double x) {
    Func der = this;
    for (int i = 0; i < order; ++i) {
      der = derivative(der);
    }
    return der.solve(x);
  }

}
