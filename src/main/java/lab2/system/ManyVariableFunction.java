package lab2.system;

public interface ManyVariableFunction {

    double EPS = 1e-4;

    double solve(double ... vars);

    default double partialDerivative(int index, double ... vars) {
        double oldF = solve(vars);
        vars[index] += EPS;
        double newF = solve(vars);
        vars[index] -= EPS;
        return (newF - oldF) / EPS;
    }
}
