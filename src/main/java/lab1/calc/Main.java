package lab1.calc;

import lab1.utils.Input;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    double accuracy = 1e-8;
    if (args.length > 0) {
      accuracy = Double.parseDouble(args[0]);
    }

    Input reader = new Input();

    EqSystem m;
    try {
      m = reader.readMatrix();
    } catch (IOException e) {
      System.out.println("Unable to read. " + e.getMessage());
      return;
    } catch (NumberFormatException e) {
      System.out.println("Wrong input format. " + e.getMessage());
      return;
    }

    Result result;
    if (m.hasSolvation()) {
      result = LinearAlgebra.solve(m, accuracy);
    } else {
      System.out.println("Unable to solve system with this method");
      return;
    }

    if (result.isValid()) {
      System.out.println(result);
    } else {
      System.out.println("Unable to solve with this method, system diverges");
    }

  }
}
