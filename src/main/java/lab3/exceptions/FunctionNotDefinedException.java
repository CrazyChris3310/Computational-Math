package lab3.exceptions;

public class FunctionNotDefinedException extends RuntimeException {
  public FunctionNotDefinedException(String message) {
    super(message);
  }
}
