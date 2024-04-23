package Task2345;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IntegerCalculatorResultTests {

    @Test
    void testAddition() {
        CalculatorRequest request = new CalculatorRequest(5, 3, "+");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertEquals(8, result.computeResult());
    }

    @Test
    void testDivision() {
        CalculatorRequest request = new CalculatorRequest( 10, 2, "/");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertEquals(5, result.computeResult());
    }

    @Test
    void testDivisionByZero() {
        CalculatorRequest request = new CalculatorRequest(10, 0, "/");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertThrows(ArithmeticException.class, result::computeResult);
    }

    @Test
    void testUnsupportedOperation() {
        CalculatorRequest request = new CalculatorRequest( 10, 5, "^");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertThrows(IllegalArgumentException.class, result::computeResult);
    }

    @Test
    void testInvalidOperand() {
        CalculatorRequest request = new CalculatorRequest("ten", "5", "+");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertThrows(NumberFormatException.class, result::computeResult);
    }

    @Test
    void testNullOperand() {
        CalculatorRequest request = new CalculatorRequest(null, "5", "+");
        IntegerCalculatorResult result = new IntegerCalculatorResult(request);
        assertThrows(NullPointerException.class, result::computeResult);
    }
}

class DoubleCalculatorResultTests {

    @Test
    void testMultiplication() {
        CalculatorRequest request = new CalculatorRequest("10.5", "2", "*");
        DoubleCalculatorResult result = new DoubleCalculatorResult(request);
        assertEquals(21.0, result.computeResult());
    }

    @Test
    void testDivisionByZero() {
        CalculatorRequest request = new CalculatorRequest("10.5", "0.0", "/");
        DoubleCalculatorResult result = new DoubleCalculatorResult(request);
        assertThrows(ArithmeticException.class, result::computeResult);
    }
}

class BooleanCalculatorResultTests {

    @Test
    void testAndOperationTrueTrue() {
        CalculatorRequest request = new CalculatorRequest("true", "true", "AND");
        BooleanCalculatorResult result = new BooleanCalculatorResult(request);
        assertTrue((Boolean) result.computeResult());
    }

    @Test
    void testOrOperationTrueFalse() {
        CalculatorRequest request = new CalculatorRequest( "true", "false", "OR");
        BooleanCalculatorResult result = new BooleanCalculatorResult(request);
        assertTrue((Boolean) result.computeResult());
    }

    @Test
    void testUnsupportedOperation() {
        CalculatorRequest request = new CalculatorRequest("true", "false", "XOR");
        BooleanCalculatorResult result = new BooleanCalculatorResult(request);
        assertThrows(IllegalArgumentException.class, result::computeResult);
    }
}

