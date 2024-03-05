package lab1.task4;

import java.util.Scanner;

public class DummyCalculator {


    public void calculate(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid arguments. Check your input");
            return;
        }

        String operand1 = args[0];
        String operator = args[1];
        String operand2 = args[2];

        try {
            if (operand1.contains(".") || operand2.contains(".")) {
                Double op1 = Double.parseDouble(operand1);
                Double op2 = Double.parseDouble(operand2);
                calculateDouble(op1, op2, operator);
            } else if(operand1.contains("true") || operand1.contains("false"))
            {
                Boolean op1 = Boolean.parseBoolean(operand1);
                Boolean op2 = Boolean.parseBoolean(operand2);
                calculateBoolean(op1, op2, operator);
            }
            else {
                Integer op1 = Integer.parseInt(operand1);
                Integer op2 = Integer.parseInt(operand2);
                calculateInteger(op1, op2, operator);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numbers ");
        }
    }
    private void calculateBoolean(Boolean op1, Boolean op2, String operator) {
        switch (operator) {
            case "&":
                System.out.println(op1 && op2);
                break;
            case "|":
                System.out.println(op1 || op2);
                break;
            default:
                break;
        }
    }
    private void calculateDouble(double op1, double op2, String operator) {
        switch (operator) {
            case "+":
                System.out.println(op1 + op2);
                break;
            case "-":
                System.out.println(op1 - op2);
                break;
            case "*":
                System.out.println(op1 * op2);
                break;
            case "/":
                if (op2 != 0) {
                    System.out.println(op1 / op2);
                } else {
                    System.out.println("Cannot divide by zero");
                }
                break;
            default:
                break;
        }
    }

    private void calculateInteger(int op1, int op2, String operator) {
        switch (operator) {
            case "+":
                System.out.println(op1 + op2);
                break;
            case "-":
                System.out.println(op1 - op2);
                break;
            case "*":
                System.out.println(op1 * op2);
                break;
            case "/":
                if (op2 != 0) {
                    System.out.println((double) op1 / op2);
                } else {
                    System.out.println("Cannot divide by zero");
                }
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        DummyCalculator calculator = new DummyCalculator();
        while(true) {
            Scanner in = new Scanner(System.in);

            String s = in.nextLine();

            String[] values = s.split("\\s");

            if(values[0].equals("quit"))
                return;
            calculator.calculate(values);
        }
    }
}
