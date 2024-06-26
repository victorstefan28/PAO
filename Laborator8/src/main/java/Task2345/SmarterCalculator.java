package Task2345;

import java.util.ArrayList;
import java.util.List;

record CalculatorRequest(Object leftOperand, Object rightOperand, String operation) {
    public String getRequestType() {
        if (leftOperand instanceof Boolean || rightOperand instanceof Boolean) {
            return "Boolean";
        } else if (leftOperand instanceof Double || rightOperand instanceof Double) {
            return "Double";
        } else {
            return "Integer";
        }
    }

    @Override
    public String toString() {
        return leftOperand + " " + operation + " " + rightOperand;
    }
}

abstract class CalculatorResult {
    protected final CalculatorRequest calculatorRequest;

    protected CalculatorResult(CalculatorRequest calculatorRequest) {
        this.calculatorRequest = calculatorRequest;
    }

    public abstract Object computeResult();

    public CalculatorRequest getRequest() {
        return calculatorRequest;
    }
}

final class InputConverter {
    public static List<CalculatorRequest> mapRequests(String[] args) {
        List<CalculatorRequest> requests = new ArrayList<>();
        for (int i = 0; i < args.length; i += 3) {
            Object leftOperand = parseOperand(args[i]);
            String operation = args[i + 1];
            Object rightOperand = parseOperand(args[i + 2]);

            requests.add(new CalculatorRequest(leftOperand, rightOperand, operation));
        }
        return requests;
    }

    private static Object parseOperand(String operand) {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(operand);
            } catch (NumberFormatException e2) {
                return Boolean.parseBoolean(operand);
            }
        }
    }
}

final class IntegerCalculatorResult extends CalculatorResult {
    public IntegerCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Integer computeResult() {
        int leftOperand = (Integer) calculatorRequest.leftOperand();
        int rightOperand = (Integer) calculatorRequest.rightOperand();
        switch (calculatorRequest.operation()) {
            case "+":
                return leftOperand + rightOperand;
            case "-":
                return leftOperand - rightOperand;
            case "*":
                return leftOperand * rightOperand;
            case "/":
                if (rightOperand == 0) throw new ArithmeticException("Division by zero");
                return leftOperand / rightOperand;
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
    }
}

final class DoubleCalculatorResult extends CalculatorResult {
    public DoubleCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() {
        double leftOperand = Double.parseDouble(calculatorRequest.leftOperand().toString());
        double rightOperand = Double.parseDouble(calculatorRequest.rightOperand().toString());
        switch (calculatorRequest.operation()) {
            case "+":
                return leftOperand + rightOperand;
            case "-":
                return leftOperand - rightOperand;
            case "*":
                return leftOperand * rightOperand;
            case "/":
                if (rightOperand == 0) throw new ArithmeticException("Division by zero");
                return leftOperand / rightOperand;
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
    }
}

final class BooleanCalculatorResult extends CalculatorResult {
    public BooleanCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() {
        boolean leftOperand = Boolean.parseBoolean(calculatorRequest.leftOperand().toString());
        boolean rightOperand = Boolean.parseBoolean(calculatorRequest.rightOperand().toString());
        switch (calculatorRequest.operation()) {
            case "AND":
                return leftOperand && rightOperand;
            case "OR":
                return leftOperand || rightOperand;
            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
    }
}

class SmarterCalculator {
    public static List<CalculatorResult> calculate(String[] args) {
        List<CalculatorRequest> requests = InputConverter.mapRequests(args);
        List<CalculatorResult> results = new ArrayList<>();

        for (CalculatorRequest request : requests) {
            switch (request.getRequestType()) {
                case "Integer":
                    results.add(new IntegerCalculatorResult(request));
                    break;
                case "Double":
                    results.add(new DoubleCalculatorResult(request));
                    break;
                case "Boolean":
                    results.add(new BooleanCalculatorResult(request));
                    break;
            }
        }

        return results;
    }
}

class Main {
    public static void main(String[] args) {
        String[] arguments = {"3", "+", "5", "10.5", "*", "4.5", "true", "AND", "false"};
        foo(arguments);
    }

    public static void foo(String[] args) {
        List<CalculatorResult> calculationResults = SmarterCalculator.calculate(args);

        for (CalculatorResult result : calculationResults) {
            CalculatorRequest request = result.getRequest();
            System.out.println("Operation: " + request + " has result " + result.computeResult());
        }
    }
}
