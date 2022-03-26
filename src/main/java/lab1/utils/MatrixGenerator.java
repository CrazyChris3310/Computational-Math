package lab1.utils;

import lab1.calc.EqSystem;

public class MatrixGenerator {

    private static final double MIN = -100;
    private static final double MAX = 100;

    private static double[] generateAnswers() {
        int size = (int)(Math.random() * 21);
        return generateAnswers(size);
    }

    private static double[] generateAnswers(int size) {
        double[] ans = new double[size];
        for (int i = 0; i < size; ++i) {
            ans[i] = Math.random() * (MAX - MIN) + MIN;
        }
        return ans;
    }

    private static EqSystem generateSystem(double[] ans) {
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

    private static void outputSystem(int size) {
        double[] ans = generateAnswers(size);
        EqSystem sys = generateSystem(ans);

        StringBuilder output = new StringBuilder();
        output.append(size).append("\n");

        for (int i = 0; i < size; ++i) {
            double[] row = sys.getRow(i);
            for (double num : row) {
                output.append(String.format("%.3f", num)).append(" ");
            }
            output.append(String.format("%.3f", sys.getB(i))).append("\n");
        }
        System.out.println(output);
    }

    public static void main(String[] args) {
        outputSystem(20);
        System.out.println();
        outputSystem(0);
        System.out.println();
        outputSystem(10);
        outputSystem(4);
        outputSystem(17);
    }

}
