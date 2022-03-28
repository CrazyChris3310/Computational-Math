package lab1.utils;

import lab1.calc.EqSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {

    private final Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
    }

    private int readSize() {
        return readInt();
    }

    public EqSystem readMatrix() throws IOException {
        int size = readSize();

        EqSystem m = new EqSystem(size);
        double[] bs = new double[size];

        for (int i = 0; i < size; ++i) {
            String line = sc.nextLine();
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

    public double readDouble() {
        String str;
        Double res = null;
        do {
            str = sc.nextLine().replaceAll(",", ".");
            try {
                res = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                System.out.print("Wrong input format. Try again: ");
            }
        } while (res == null);
        return res;
    }

    public int readInt() {
        String str;
        Integer res = null;
        do {
            str = sc.nextLine().replaceAll(",", ".");
            try {
                res = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.print("Wrong input format. Try again: ");
            }
        } while (res == null);
        return res;
    }

}
