package lab1.calc;

public class EqSystem {

    private int size;
    private double[][] coefs;
    private double[] bs;

    public EqSystem(int size) {
        this.size = size;
        coefs = new double[size][size];
        bs = new double[size];
    }

    public void setRow(int i, double[] row) {
        coefs[i] = row;
    }

    public double[] getRow(int i) {
        return coefs[i];
    }

    public void setElement(int i, int j, double val) {
        coefs[i][j] = val;
    }

    public double getElement(int i, int j) {
        return coefs[i][j];
    }

    public int getSize() {
        return size;
    }

    public void setBs(double[] bs) {
        this.bs = bs;
    }

    public double getB(int i) {
        return bs[i];
    }

    public void setB(int i, double val) {
        bs[i] = val;
    }

    private boolean diagonalIsCorrect() {
        boolean correct = true;
        boolean wasLess = false;
        for (int i = 0; i < size; ++i) {
            double sum = 0;
            for (int j = 0; j < size; ++j) {
                sum += Math.abs(this.coefs[i][j]);
            }
            sum -= Math.abs(this.coefs[i][i]);
            if (sum > this.coefs[i][i]) {
                correct = false;
                break;
            } else if (sum < this.coefs[i][i])
                wasLess = true;
        }
        return wasLess && correct;
    }

    private boolean noZeroesOnDiagonal() {
        for (int i = 0; i < size; ++i) {
            if (coefs[i][i] == 0) {
                boolean found = false;
                for (int j = 0; j < size; ++j) {
                    if (coefs[j][i] != 0 && coefs[i][j] != 0) {
                        double[] row = getRow(j);
                        setRow(j, getRow(i));
                        setRow(i, row);
                        double b = getB(j);
                        setB(j, getB(i));
                        setB(i, b);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasSolvation() {
        return noZeroesOnDiagonal() && diagonalIsCorrect();
    }

    public double[][] getCoefs() {
        return coefs;
    }

    public void setCoefs(double[][] coefs) {
        this.coefs = coefs;
    }

    public double[] getBs() {
        return bs;
    }
}
