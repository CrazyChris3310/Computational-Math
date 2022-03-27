package lab2.system;

import lab2.equation.Expression;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class NonLinearSystem {

    private final List<ManyVariableFunction> system = new LinkedList<>();
    private static final int LIMIT = 20000;
    private static final double ACCURACY = 1e-4;

    public static final List<NonLinearSystem> NON_LINEAR_SYSTEMS;

    static {
        NON_LINEAR_SYSTEMS = new LinkedList<>();

        NonLinearSystem sys = new NonLinearSystem();
        sys.addFunction(Functions::func1);
        sys.addFunction(Functions::func2);
        NON_LINEAR_SYSTEMS.add(sys);

        sys = new NonLinearSystem();
        sys.addFunction(Functions::func3);
        sys.addFunction(Functions::func4);
        NON_LINEAR_SYSTEMS.add(sys);

        sys = new NonLinearSystem();
        sys.addFunction(Functions::func4);
        sys.addFunction(Functions::func1);
        NON_LINEAR_SYSTEMS.add(sys);
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
        } while (delta > ACCURACY && count < LIMIT);

        return newAns;
    }

    public void addFunction(ManyVariableFunction f) {
        system.add(f);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        try {
            for (ManyVariableFunction f : system) {
                Method method = f.getClass().getMethod("solve", double[].class);
                Expression expression = method.getAnnotation(Expression.class);
                if (expression != null) {
                    builder.append(expression.formula()).append("\n");
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
