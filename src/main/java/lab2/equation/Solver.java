package lab2.equation;

import java.util.LinkedList;

public class Solver {

    private static final double EPS = 1e-3;

    public double[][] findBorders(Func f, double leftBorder, double rightBorder) {
        LinkedList<double[]> result = new LinkedList<>();
        double prev = leftBorder;
        double step = 0.1;
        for (double x = leftBorder + step; x <= rightBorder; x += step) {
            double num = f.solve(x);
            if (!Double.isNaN(num)){
                if (Math.abs(num) <= EPS)
                    continue;
                if (num * f.solve(prev) < 0)
                    result.add(new double[] {prev, x});
            }
            prev = x;
        }
        return result.toArray(new double[result.size()][2]);
    }

    public double[][] solveEquation(Func equation, double leftBorder, double rightBorder) {
        double[][] rootBorders = findBorders(equation, leftBorder, rightBorder);
        if (rootBorders.length == 0)
            return null;

        double[][] result = new double[2][rootBorders.length];

        for (int i = 0; i < rootBorders.length; ++i) {
            result[0][i] = solveNewton(equation, rootBorders[i][0], rootBorders[i][1]);
            result[1][i] = solveIterations(equation, rootBorders[i][0], rootBorders[i][1]);
        }

        return result;
    }

    public Double solveNewton(Func f, double a, double b) {
        double x0;
        if (f.solve(a) * f.derivative(2, a) <= 0) {
            if (f.solve(b) * f.derivative(2, b) <= 0)
                return null;
            else
                x0 = b;
        } else
            x0 = a;

        double x1 = x0 - f.solve(x0) / f.derivative(x0);
        while (Math.abs(x1 - x0) >= EPS) {
            x0 = x1;
            x1 = x0 - f.solve(x0) / f.derivative(x0);
        }
        return x1;
    }

    public Double solveIterations(Func f, double a, double b) {
        double max_derivative = f.derivative(a);
        for (double dx = a; dx <= b; dx += 0.01) {
            double der = f.derivative(dx);
            if (der > max_derivative)
                max_derivative = der;
        }
        double lambda = -1 / max_derivative;

        double q = Math.abs(1 + lambda * f.derivative(a));
        for (double dx = a; dx <= b; dx += 0.01) {
            double der = Math.abs(1 + lambda * f.derivative(dx));
            if (der > q)
                q = der;
        }

        double accuracy = q <= 0.5 ? EPS : (1 - q) / q * EPS;

        double prev;
        double num = a;
        do {
            prev = num;
            num = prev + lambda * f.solve(prev);
        } while (num - prev > accuracy);

        return num;
    }

}
