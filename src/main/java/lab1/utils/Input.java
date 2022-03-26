package lab1.utils;

import lab1.calc.EqSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    private final BufferedReader reader;

    public Input() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private int readSize() throws IOException {
        String num = reader.readLine();
        return Integer.parseInt(num);
    }

    public EqSystem readMatrix() throws IOException {
        int size = readSize();

        EqSystem m = new EqSystem(size);
        double[] bs = new double[size];

        for (int i = 0; i < size; ++i) {
            String line = reader.readLine();
            line = line.replaceAll(",", ".");
            double[] row = new double[size];
            String[] splited = line.split(" ");
            for (int j = 0; j < size; ++j) {
                row[j] = Double.parseDouble(splited[j]);
            }
            bs[i] = Double.parseDouble(splited[size]);
            m.setRow(i, row);
        }
        m.setBs(bs);

        return m;
    }

}
