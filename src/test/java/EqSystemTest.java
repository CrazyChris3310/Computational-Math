import lab1.calc.EqSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqSystemTest {

  @Test
  void testHasSolvationPositive() {
    EqSystem sys = new EqSystem(4);
    sys.setRow(0, new double[]{ 4, 1, 1, 1 });
    sys.setRow(1, new double[]{ 2, 8, 2, 2 });
    sys.setRow(2, new double[]{ 1, 2, 7, 3 });
    sys.setRow(3, new double[]{ 1, 2, 3, 10 });
    assertTrue(sys.hasSolvation());
  }

  @Test
  void testHasSolvationNegative() {
    EqSystem sys = new EqSystem(4);
    assertFalse(sys.hasSolvation());
  }

  @Test
  void testHasSolvationPositiveWithShifts() {
    EqSystem sys = new EqSystem(4);
    sys.setRow(0, new double[]{ 4, 1, 1, 1 });
    sys.setRow(1, new double[]{ 2, 8, 2, 2 });
    sys.setRow(2, new double[]{ 1, 2, 0, 3 });
    sys.setRow(3, new double[]{ 1, 2, 3, 10 });
    sys.setElement(2, 2, 0);
    assertFalse(sys.hasSolvation());
  }

  @Test
  void testHasSolvationPositiveWithOneShiftPossible() {
    EqSystem sys = new EqSystem(4);
    sys.setRow(0, new double[]{ 4, 1, 1, 1 });
    sys.setRow(1, new double[]{ 2, 8, 0, 2 });
    sys.setRow(2, new double[]{ 0, 2, 0, 3 });
    sys.setRow(3, new double[]{ 1, 2, 10, 5 });
    assertTrue(sys.hasSolvation());
  }

  @Test
  void testHasSolvationNegativeBecauseNoShiftsPossible() {
    EqSystem sys = new EqSystem(4);
    sys.setRow(0, new double[]{ 1, 1, 1, 1 });
    sys.setRow(1, new double[]{ 2, 2, 0, 2 });
    sys.setRow(2, new double[]{ 0, 3, 0, 0 });
    sys.setRow(3, new double[]{ 4, 4, 4, 4 });
    assertFalse(sys.hasSolvation());
  }
}