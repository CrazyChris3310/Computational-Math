package lab2.system;

import lab1.calc.EqSystem;
import lab1.calc.LinearAlgebra;

import java.util.LinkedList;
import java.util.List;

public class NonLinearSystem {

    private final List<ManyVariableFunction> system = new LinkedList<>();
    private static final int LIMIT = 20000;
    private final double accuracy = 1e-4;

    public double[] solveNewton(double ... X) {

        boolean shouldContinue;

        do {
            double[][] jacobian = findJacobian(X);
            double[] bs = new double[system.size()];
            for (int i = 0; i < system.size(); ++i) {
                bs[i] = -system.get(i).solve(X);
            }

            EqSystem sys = new EqSystem(system.size());
            sys.setCoefs(jacobian);
            sys.setBs(bs);

            double[] deltas = LinearAlgebra.solveGaussian(sys, 1e-5);
            if (deltas == null)
                return null;

            shouldContinue = false;
            for (int i = 0; i < X.length; ++i) {
                X[i] += deltas[i];
                if (deltas[i] > accuracy)
                    shouldContinue = true;
            }

        } while (shouldContinue);

        return X;
    }

    private double[][] findJacobian(double ... vars) {
        double[][] jacobian = new double[vars.length][vars.length];
        for (int i = 0; i < vars.length; ++i) {
            for (int j = 0; j < vars.length; ++j) {
                jacobian[i][j] = system.get(i).partialDerivative(j, vars);
            }
        }
        return jacobian;
    }

    public double[] findSolution(double ... point) {
        // FIXME: better to throw exceptions

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
        if (max > 1)
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
        } while (delta > accuracy && count < LIMIT);

        return newAns;
    }

    public void addFunction(ManyVariableFunction f) {
        system.add(f);
    }

}
