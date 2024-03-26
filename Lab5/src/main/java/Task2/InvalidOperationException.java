package Task2;

public class InvalidOperationException extends CalculatorException{
    public InvalidOperationException(String operand1, String operand2, String operator) {
        super(operand1 + " " + operator + " " + operand2 + " is not a valid operation.");
    }
}
