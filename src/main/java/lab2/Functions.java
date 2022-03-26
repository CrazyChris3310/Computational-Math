package lab2;

public class Functions {

    private static final double dx = 1e-8;

    public static double thirdGrade(double x) {
        return 5*Math.pow(x, 3) + 8*Math.pow(x, 2) - 2.4*x - 1;
    }

    public static double fourthGrade(double x) {
        return 14*Math.pow(x, 4) - 4*x*x + 7*x - 3;
    }

    public static double exponent(double x) {
        return Math.exp(2*x) + x*x - 12;
    }

    public static double sinus(double x) {
        return 3*Math.pow(x, 3) + 5*Math.sin(x) - 4;
    }

    public static double logarithmic(double x) {
        return 10 * Math.log(x);
    }

}