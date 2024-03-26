package Task2;

public class UnknownOperandTypeException extends CalculatorException {
    public UnknownOperandTypeException(String operand) {
        super(operand + "'s type is not valid.(" + operand.getClass() + ")");
    }
}
