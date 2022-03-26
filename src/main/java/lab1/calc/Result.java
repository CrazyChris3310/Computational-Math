package lab1.calc;

import java.util.Arrays;

public class Result {
    private long duration;
    private double[] ans;
    private double[] error;
    private int iterationCount;
    private boolean valid;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double[] getAns() {
        return ans;
    }

    public void setAns(double[] ans) {
        this.ans = ans;
    }

    public double[] getError() {
        return error;
    }

    public void setError(double[] error) {
        this.error = error;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        String str = "Result:\n";
        str += Arrays.toString(ans) + "\n";
        str += "Duration: " + duration + "mks\n";
        str += "Iterations count: " + iterationCount + "\n";
        str += "Errors: " + Arrays.toString(error) + "\n";
        return str;
    }
}
